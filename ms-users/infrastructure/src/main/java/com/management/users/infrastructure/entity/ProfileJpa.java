package com.management.users.infrastructure.entity;

import com.management.users.domain.constants.Global;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "profiles", schema = Global.SCHEMA_DATABASE)
@Data
@EqualsAndHashCode
public class ProfileJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_profile", nullable = false)
  private Long id;

  @Column(name = "photo")
  private String photo;

  @Column(name = "description")
  private String description;

  @Column(name = "interest")
  private String interest;
}
