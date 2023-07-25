package com.management.users.domain.service;

import com.management.users.domain.model.entity.User;

import java.util.List;

public interface UserService {

  List<User> findAllUsers();

  User findByUsername(String username);

  User findById(Long id);

  User update(User user);

  User updatePassword(String password, Long id);

  User create(User user);

  void delete(Long id);


}
