package com.example.demoreactorbc.infrastructure.web.in.shared;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import com.example.demoreactorbc.application.shared.GenericServiceAPI;
import java.io.Serializable;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public abstract class GenericHandler<T, ID extends Serializable> {

  private final GenericServiceAPI<T, ID> serviceAPI;
  private final Validator validator;

  public GenericHandler(GenericServiceAPI<T, ID> serviceAPI, Validator validator) {
    this.serviceAPI = serviceAPI;
    this.validator = validator;
  }

  public Mono<ServerResponse> getAll(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(serviceAPI.getAll(), Object.class);
  }

  public Mono<ServerResponse> find(ServerRequest request) {
    ID id = getIdFromPath(request);
    return serviceAPI.getOne(id)
        .flatMap(entity -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromValue(entity)))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> save(ServerRequest request) {
    Mono<T> entityMono = request.bodyToMono(getGenericTypeClass());
    return entityMono.flatMap(entity -> {
      Errors errors = new BeanPropertyBindingResult(entity, getGenericTypeClass().getName());
      validator.validate(entity, errors);

      if (errors.hasErrors()) {
        return Mono.fromSupplier(errors::getFieldErrors)
            .flatMap(fieldErrors -> ServerResponse.badRequest()
                .body(fromValue(fieldErrors.stream()
                    .map(error -> "El campo " + error.getField() + " " + error.getDefaultMessage())
                    .toList())));
      }

      // Si el ID no está presente, lo generamos
//      if (getIdFromEntity(entity) == null) {
//        // Generar el ID para la entidad
//        try {
//          entity = generateId(entity); // Aquí generamos el ID
//        } catch (NoSuchAlgorithmException e) {
//          return Mono.error(new RuntimeException(e));
//        }
//      }

      // Guardar la entidad con el ID generado, tratando siempre de hacer un INSERT
      try {
        return serviceAPI.save(entity)
            .flatMap(savedEntity -> {
              // Aquí ya tenemos la entidad con un ID generado (si es nuevo)
              return ServerResponse.created(getURIPath(savedEntity))
                  .contentType(MediaType.APPLICATION_JSON)
                  .body(fromValue(savedEntity));
            });
      } catch (NoSuchAlgorithmException e) {
        return Mono.error(new RuntimeException(e));
      }
    });
  }


  public Mono<ServerResponse> delete(ServerRequest request) {
    ID id = getIdFromPath(request);
    return serviceAPI.getOne(id)
        .flatMap(entity -> serviceAPI.delete(id)
            .then(ServerResponse.noContent().build()))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  protected abstract Class<T> getGenericTypeClass();

  protected abstract ID getIdFromPath(ServerRequest request);

  protected abstract ID getIdFromEntity(T entity);

  protected abstract URI getURIPath(T entity);

}

