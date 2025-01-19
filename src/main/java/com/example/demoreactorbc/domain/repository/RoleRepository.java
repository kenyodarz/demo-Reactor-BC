package com.example.demoreactorbc.domain.repository;

import com.example.demoreactorbc.domain.models.Role;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleRepository {

  Flux<Role> getAll();

  Mono<Role> getById(String id);

  Mono<Role> save(Role role) throws NoSuchAlgorithmException;

  Mono<Void> delete(String id);

}
