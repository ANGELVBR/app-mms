package com.management.users.domain.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode
public class RoleDto {

  private Integer id;

  private String code;

  private String description;


}
