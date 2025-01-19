package com.example.demoreactorbc.infrastructure.web.in.handler;

import com.example.demoreactorbc.application.services.apis.RoleServiceAPI;
import com.example.demoreactorbc.domain.models.Role;
import com.example.demoreactorbc.infrastructure.web.in.shared.GenericHandler;
import java.net.URI;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class RoleHandler extends GenericHandler<Role, String> {

  public RoleHandler(RoleServiceAPI serviceAPI, Validator validator) {
    super(serviceAPI, validator);
  }

  @Override
  protected Class<Role> getGenericTypeClass() {
    return Role.class;
  }

  @Override
  protected String getIdFromPath(ServerRequest request) {
    return request.pathVariable("id");  // Convierte el String a UUID
  }

  @Override
  protected String getIdFromEntity(Role entity) {
    try {
      // Intentar convertir el roleId a UUID
      return entity.getRoleId() != null ? entity.getRoleId() : null;
    } catch (IllegalArgumentException e) {
      // Si el formato no es válido, retornar null
      return null;
    }
  }

  @Override
  protected URI getURIPath(Role entity) {
    return URI.create("/api/v1/role/" + getIdFromEntity(entity));
  }


}
