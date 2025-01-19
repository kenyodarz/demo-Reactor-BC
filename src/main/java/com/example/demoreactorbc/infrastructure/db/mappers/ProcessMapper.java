package com.example.demoreactorbc.infrastructure.db.mappers;

import com.example.demoreactorbc.domain.models.Process;
import com.example.demoreactorbc.infrastructure.db.entity.Proceso;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProcessMapper {

  @Mapping(target = "processId", source = "idProceso")
  @Mapping(target = "name", source = "nombre")
  Process toProcess(Proceso proceso);

  @InheritInverseConfiguration
  Proceso toProceso(Process process);
}
