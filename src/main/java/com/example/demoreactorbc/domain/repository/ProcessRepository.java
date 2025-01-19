package com.example.demoreactorbc.domain.repository;

import com.example.demoreactorbc.domain.models.Process;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProcessRepository {

  Flux<Process> getAll();

  Mono<Process> getById(String id);

  Mono<Process> save(Process process) throws NoSuchAlgorithmException;

  Mono<Void> delete(String id);

}
