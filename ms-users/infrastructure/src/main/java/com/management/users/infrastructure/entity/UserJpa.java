package com.management.users.infrastructure.entity;

import com.management.users.domain.constants.Global;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users", schema = Global.SCHEMA_DATABASE)
@Data
@EqualsAndHashCode
public class UserJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_user", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1)")
  private Boolean active;

  @Temporal(TemporalType.DATE)
  @Column(name = "createDate", nullable = false)
  private Date createDate;

  @ManyToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
  @JoinTable(
          name = "USERS_ROLES",
          joinColumns = {@JoinColumn(name = "ID_USER")},
          inverseJoinColumns = {@JoinColumn(name = "ID_ROL")})
  private List<RoleJpa> roles = new ArrayList<>();

}
