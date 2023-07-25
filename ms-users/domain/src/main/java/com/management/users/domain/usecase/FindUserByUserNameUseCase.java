package com.management.users.domain.usecase;

import com.management.users.domain.model.entity.User;

public interface FindUserByUserNameUseCase {

  User findUserByUserName(String userName);

}
