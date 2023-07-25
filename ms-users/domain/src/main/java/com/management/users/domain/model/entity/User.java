package com.management.users.domain.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@EqualsAndHashCode
public class User {

  private Long id;

  private String name;

  private String email;
  
  private String password;

  private Boolean active;

  private Date createDate;

  @Builder.Default
  private List<Role> roles = new ArrayList<>();

}
