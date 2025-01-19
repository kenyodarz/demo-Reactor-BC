package com.example.demoreactorbc.domain.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Role {
  private String roleId;
  @NotBlank
  private String name;
}
