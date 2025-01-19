package com.example.demoreactorbc.infrastructure.web.in.handler;

import com.example.demoreactorbc.application.services.apis.ProcessServiceAPI;
import com.example.demoreactorbc.domain.models.Process;
import com.example.demoreactorbc.infrastructure.web.in.shared.GenericHandler;
import java.net.URI;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class ProcessHandler extends GenericHandler<Process, String> {

  public ProcessHandler(ProcessServiceAPI serviceAPI, Validator validator) {
    super(serviceAPI, validator);
  }

  @Override
  protected Class<Process> getGenericTypeClass() {
    return Process.class;
  }

  @Override
  protected String getIdFromPath(ServerRequest request) {
    return request.pathVariable("id");
  }

  @Override
  protected String getIdFromEntity(Process entity) {
    return entity.getProcessId();
  }

  @Override
  protected URI getURIPath(Process entity) {
    return URI.create("/api/v1/process/" + getIdFromEntity(entity));
  }

}
