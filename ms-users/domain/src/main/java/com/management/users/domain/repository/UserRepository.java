package com.management.users.domain.repository;

import com.management.users.domain.model.entity.User;

import java.util.List;

public interface UserRepository {

  List<User> findAllUsers();

  User findByUsername(String username);

  User update(User user);

  User updatePassword(String password, Long id);

  User create(User user);

  User findById(Long id);

  void delete(Long id);

}
