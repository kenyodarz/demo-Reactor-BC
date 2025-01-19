package com.example.demoreactorbc.infrastructure.db.providers;

import com.example.demoreactorbc.domain.models.Process;
import com.example.demoreactorbc.domain.repository.ProcessRepository;
import com.example.demoreactorbc.infrastructure.db.entity.Proceso;
import com.example.demoreactorbc.infrastructure.db.mappers.ProcessMapper;
import com.example.demoreactorbc.infrastructure.db.repository.ProcesoR2dbcRepository;
import com.example.demoreactorbc.infrastructure.web.in.shared.IdGeneratorUtil;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProcesoRepository implements ProcessRepository {

  private final ProcesoR2dbcRepository repository;

  private final ProcessMapper mapper;


  @Override
  public Flux<Process> getAll() {
    return repository.findAll().map(mapper::toProcess);
  }

  @Override
  public Mono<Process> getById(String id) {
    return repository.findById(id).map(mapper::toProcess);
  }

  @Override
  public Mono<Process> save(Process process) throws NoSuchAlgorithmException {
    Proceso proceso = mapper.toProceso(process);
    String generatedId = IdGeneratorUtil.generateRandomAlphanumericId("PRS-", 10);
    proceso.setIdProceso(generatedId);
    return repository.save(proceso).map(mapper::toProcess);
  }

  @Override
  public Mono<Void> delete(String id) {
    return repository.deleteById(id);
  }
}
