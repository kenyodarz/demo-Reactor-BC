package com.example.demoreactorbc.infrastructure.db.repository;

import com.example.demoreactorbc.infrastructure.db.entity.Rol;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolR2dbcRepository extends R2dbcRepository<Rol, String> {


}
