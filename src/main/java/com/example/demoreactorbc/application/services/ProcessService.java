package com.example.demoreactorbc.application.services;

import com.example.demoreactorbc.application.services.apis.ProcessServiceAPI;
import com.example.demoreactorbc.domain.models.Process;
import com.example.demoreactorbc.domain.repository.ProcessRepository;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProcessService implements ProcessServiceAPI {

  private final ProcessRepository repository;

  @Override
  public Mono<Void> delete(String id) {
    return repository.delete(id);
  }

  @Override
  public Flux<Process> getAll() {
    return repository.getAll();
  }

  @Override
  public Mono<Process> getOne(String id) {
    return repository.getById(id);
  }

  @Override
  public Mono<Process> save(Process entity) throws NoSuchAlgorithmException {
    return repository.save(entity);
  }
}
