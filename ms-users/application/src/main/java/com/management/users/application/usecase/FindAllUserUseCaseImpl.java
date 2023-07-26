package com.management.users.application.usecase;

import com.management.users.domain.model.entity.User;
import com.management.users.domain.service.UserService;
import com.management.users.domain.usecase.FindAllUserUseCase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllUserUseCaseImpl implements FindAllUserUseCase {

  private final @NonNull UserService userService;

  @Override
  public List<User> findAllUser() {
    return this.userService.findAllUsers();
  }
}
