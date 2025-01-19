package com.example.demoreactorbc.application.services;

import com.example.demoreactorbc.application.services.apis.RoleServiceAPI;
import com.example.demoreactorbc.domain.models.Role;
import com.example.demoreactorbc.domain.repository.RoleRepository;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleServiceAPI {

  private final RoleRepository repository;

  @Override
  public Mono<Void> delete(String s) {
    return repository.delete(s);
  }

  @Override
  public Flux<Role> getAll() {
    return repository.getAll();
  }

  @Override
  public Mono<Role> getOne(String s) {
    return repository.getById(s);
  }

  @Override
  public Mono<Role> save(Role entity) throws NoSuchAlgorithmException {
    return repository.save(entity);
  }
}
