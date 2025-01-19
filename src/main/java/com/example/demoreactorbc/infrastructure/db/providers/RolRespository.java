package com.example.demoreactorbc.infrastructure.db.providers;

import com.example.demoreactorbc.domain.models.Role;
import com.example.demoreactorbc.domain.repository.RoleRepository;
import com.example.demoreactorbc.infrastructure.db.entity.Rol;
import com.example.demoreactorbc.infrastructure.db.mappers.RoleMapper;
import com.example.demoreactorbc.infrastructure.db.repository.RolR2dbcRepository;
import com.example.demoreactorbc.infrastructure.web.in.shared.IdGeneratorUtil;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class RolRespository implements RoleRepository {

  private final RolR2dbcRepository repository;
  private final RoleMapper mapper;

  @Override
  public Flux<Role> getAll() {
    return repository.findAll().map(mapper::toRole);
  }

  @Override
  public Mono<Role> getById(String id) {
    return repository.findById(id).map(mapper::toRole);
  }

  @Override
  @Transient
  public Mono<Role> save(Role role) throws NoSuchAlgorithmException {
    Rol rol = mapper.toRol(role);
    String generatedId = IdGeneratorUtil.generateRandomAlphanumericId("ROL-", 10);
    rol.setIdRol(generatedId);
    return repository.save(rol).map(mapper::toRole);
  }

  @Override
  public Mono<Void> delete(String id) {
    return repository.deleteById(id);
  }
}
