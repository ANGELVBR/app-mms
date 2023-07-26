package com.management.users.domain.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode
public class Role {

  private Integer id;

  private String code;

  private String description;


}
