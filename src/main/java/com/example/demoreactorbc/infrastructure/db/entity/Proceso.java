package com.example.demoreactorbc.infrastructure.db.entity;

import java.time.Instant;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("proceso")
public class Proceso implements Persistable<String> {

  @Id
  @Column("id_proceso")
  private String idProceso;
  private String nombre;
  @CreatedDate
  private Instant createdAt;

  @Transient
  private transient boolean isNew = true;

  @Override
  public String getId() {
    return idProceso;
  }

  @Override
  public boolean isNew() {
    return isNew;
  }

  public void setAsNotNew() {
    this.isNew = false;
  }
}
