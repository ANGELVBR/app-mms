package com.management.users.infrastructure.service;

import com.management.users.domain.data.UserData;
import com.management.users.domain.model.entity.User;
import com.management.users.domain.repository.UserRepository;
import com.management.users.infrastructure.data.UserJpaData;
import com.management.users.infrastructure.entity.UserJpa;
import com.management.users.infrastructure.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserServiceImpl service;

  UserData userData;

  @BeforeEach
  void setUp() {
    this.userData = new UserData();
  }

  @Test
  @DisplayName("Test by Get all users")
  void findAllUsers() {
   final List<User> userList = this.userData.getList();

    when(this.userRepository.findAllUsers()).thenReturn(userList);

    final List<User> result = this.service.findAllUsers();

    assertNotNull(result);
    assertEquals(userList.size(), result.size());
    assertEquals(userList.get(0), result.get(0));
    verify(this.userRepository).findAllUsers();

  }

  @Test
  @DisplayName("Test by get users by username")
  void findByUsername() {
     final User user = this.userData.get(1);

    when(this.userRepository.findByUsername(anyString())).thenReturn(user);

    final User result = this.service.findByUsername("username");

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userRepository).findByUsername(anyString());
   }

  @Test
  @DisplayName("findById: Test by Get users by id, user exists")
  void findById() {
    final User user = this.userData.get(1);

    when(this.userRepository.findById(anyLong())).thenReturn(user);

    final User result = this.service.findById(1L);

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userRepository).findById(anyLong());
  }

  @Test
  @DisplayName("update: Test Update users, user exists")
  void update() {
    final User user = this.userData.get(1);

    when(this.userRepository.update(any())).thenReturn(user);

    final User result = this.service.update(user);

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userRepository).update(any());

  }

  @Test
  @DisplayName("updatePassword: Test Update password, user exists")
  void updatePassword() {
    final User user = this.userData.get(1);

    when(this.userRepository.updatePassword(any(),anyLong())).thenReturn(user);

    final User result = this.service.updatePassword("password",1L);

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userRepository).updatePassword(any(),anyLong());
  }

  @Test
  @DisplayName("create: Test Create user")
  void create() {
    final User user = this.userData.get(1);

    when(this.userRepository.create(any())).thenReturn(user);

    final User result = this.service.create(user);

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userRepository).create(any());

  }

  @Test
  @DisplayName("delete: Test delete password, user exists")
  void delete() {

    doNothing().when(this.userRepository).delete(anyLong());

    this.service.delete(1L);

    verify(this.userRepository).delete(anyLong());
  }
  
}