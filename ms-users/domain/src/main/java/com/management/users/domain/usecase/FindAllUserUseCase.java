package com.management.users.domain.usecase;

import com.management.users.domain.model.entity.User;

import java.util.List;

public interface FindAllUserUseCase {

  List<User> findAllUser();

}
