package com.management.users.domain.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode
public class Profile {

  private Long id;

  private String photo;

  private String description;

  private String interest;
}
