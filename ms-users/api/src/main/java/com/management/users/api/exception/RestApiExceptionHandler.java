package com.management.users.api.exception;

import com.management.users.domain.model.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestApiExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErrorDto> handlerException(final Exception ex) {

    final String uuid = UUID.randomUUID().toString();

    LOGGER.error("Handler Exception [{}], class '{}', errorMessage '{}'", 
            uuid, ex.getClass(), ex.getMessage(), ex);
     
    ErrorDto errorResponse = ErrorDto.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
        .message(ex.getMessage())
        .uuid(uuid)
        .build();
     
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
 
}
