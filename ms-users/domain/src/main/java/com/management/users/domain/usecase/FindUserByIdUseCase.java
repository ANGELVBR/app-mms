package com.management.users.domain.usecase;

import com.management.users.domain.model.entity.User;

public interface FindUserByIdUseCase {

  User findUserById(Long id);

}
