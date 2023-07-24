package com.management.users.domain.exception;

public class ValidationException extends RuntimeException {

  private static final long serialVersionUID = -1726048349871162393L;

  public ValidationException(final String message) {
    super(message);

  }
  
}
