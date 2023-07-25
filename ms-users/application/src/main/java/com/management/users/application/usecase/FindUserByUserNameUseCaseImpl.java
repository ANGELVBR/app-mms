package com.management.users.application.usecase;

import com.management.users.domain.model.entity.User;
import com.management.users.domain.service.UserService;
import com.management.users.domain.usecase.FindUserByUserNameUseCase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserByUserNameUseCaseImpl implements FindUserByUserNameUseCase {

  private final @NonNull UserService userService;

  @Override
  public User findUserByUserName(String userName) {

    return this.userService.findByUsername(userName);
  }
}
