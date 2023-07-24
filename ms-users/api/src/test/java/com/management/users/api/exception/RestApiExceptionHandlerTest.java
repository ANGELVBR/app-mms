package com.management.users.api.exception;

import com.management.users.domain.model.dto.ErrorDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestApiExceptionHandlerTest {

  @InjectMocks
  RestApiExceptionHandler restApiExceptionHandler;
    
  @Test
  void handlerExceptionTest() {
    final Exception exception = assertThrows(Exception.class, () -> {
      Integer.parseInt("ABC");
    });
    final ErrorDto result = this.restApiExceptionHandler.handlerException(exception).getBody();
    assertNotNull(result);
    assertNotNull(result.getMessage());
  }
    
  
}