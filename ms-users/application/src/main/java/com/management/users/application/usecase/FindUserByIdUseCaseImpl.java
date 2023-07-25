package com.management.users.application.usecase;

import com.management.users.domain.model.entity.User;
import com.management.users.domain.service.UserService;
import com.management.users.domain.usecase.FindUserByIdUseCase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserByIdUseCaseImpl implements FindUserByIdUseCase {

  private final @NonNull UserService userService;

  @Override
  public User findUserById(Long id) {
    return this.userService.findById(id);
  }
}

