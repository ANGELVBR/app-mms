package com.management.users.infrastructure.entity;

import com.management.users.domain.constants.Global;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "roles", schema = Global.SCHEMA_DATABASE)
@Data
@EqualsAndHashCode
public class RoleJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_rol", nullable = false)
  private Integer id;

  @Column(name = "code", nullable = false)
  private String code;

  @Column(name = "description", nullable = false)
  private String description;
}
