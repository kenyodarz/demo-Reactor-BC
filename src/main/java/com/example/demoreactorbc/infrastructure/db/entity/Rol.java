package com.example.demoreactorbc.infrastructure.db.entity;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("rol")
public class Rol implements Persistable<String> {

  @Id
  @Column("id_rol")
  private String idRol;

  private String nombre;

  @CreatedDate
  private Instant createdAt;

  @Transient
  private transient boolean isNew = true;

  @Override
  public String getId() {
    return idRol;
  }

  @Override
  public boolean isNew() {
    return isNew;
  }

  public void setAsNotNew() {
    this.isNew = false;
  }
}
