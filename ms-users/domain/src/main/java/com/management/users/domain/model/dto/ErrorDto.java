package com.management.users.domain.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {

  private String status;
  
  private String message;
  
  private String uuid;
    
}
