package com.example.demoreactorbc.infrastructure.db.mappers;

import com.example.demoreactorbc.domain.models.Role;
import com.example.demoreactorbc.infrastructure.db.entity.Rol;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

  @Mapping(target = "roleId", source = "idRol")
  @Mapping(target = "name", source = "nombre")
  Role toRole(Rol rol);

  @InheritInverseConfiguration
  Rol toRol(Role role);

}
