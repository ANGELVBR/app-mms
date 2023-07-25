package com.management.users.domain.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@EqualsAndHashCode
public class UserDto {

  private Long id;

  private String name;

  private String email;

  private Boolean active;

  private Date createDate;

  @Builder.Default
  private List<RoleDto> roles = new ArrayList<>();

}
