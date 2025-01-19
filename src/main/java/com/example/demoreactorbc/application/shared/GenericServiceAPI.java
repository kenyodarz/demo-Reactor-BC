package com.example.demoreactorbc.application.shared;

import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface GenericServiceAPI <T, ID> {
  Flux<T> getAll();
  Mono<T> getOne(ID id);
  Mono<T> save(T entity) throws NoSuchAlgorithmException;
  Mono<Void> delete(ID id);

}
