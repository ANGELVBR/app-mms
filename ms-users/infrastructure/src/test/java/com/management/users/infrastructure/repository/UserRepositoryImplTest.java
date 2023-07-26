package com.management.users.infrastructure.repository;

import com.management.users.domain.data.UserData;
import com.management.users.domain.model.entity.User;
import com.management.users.infrastructure.data.UserJpaData;
import com.management.users.infrastructure.entity.UserJpa;
import com.management.users.infrastructure.mapper.UserMapper;
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
class UserRepositoryImplTest {

  @Mock
  UserRepositoryJpa userRepositoryJpa;

  @Mock
  UserMapper userMapper;

  @InjectMocks
  UserRepositoryImpl repository;

  UserData userData;
  UserJpaData userJpaData;

  @BeforeEach
  void setUp() {
    this.userData = new UserData();
    this.userJpaData = new UserJpaData();
  }
  
  @Test
  @DisplayName("Test by Get all users repository")
  void findAllUsers() {
    final List<UserJpa> userJpaList = this.userJpaData.getList();
    final User user = this.userData.get(1);

    when(this.userRepositoryJpa.findAll()).thenReturn(userJpaList);
    when(this.userMapper.toEntity(any())).thenReturn(user);

    final List<User> result = this.repository.findAllUsers();

    assertNotNull(result);
    assertEquals(userJpaList.size(), result.size());
    assertEquals(user, result.get(0));
    verify(this.userRepositoryJpa).findAll();
    verify(this.userMapper, times(5)).toEntity(any());
  }

  @Test
  @DisplayName("Test by Get users repository by username")
  void findByUsername() {
    final UserJpa userJpa = this.userJpaData.get(1);
    final User user = this.userData.get(1);

    when(this.userRepositoryJpa.findByEmailAndActiveIsTrue(anyString())).thenReturn(Optional.of(userJpa));
    when(this.userMapper.toEntity(any())).thenReturn(user);

    final User result = this.repository.findByUsername("username");

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userRepositoryJpa).findByEmailAndActiveIsTrue(anyString());
    verify(this.userMapper).toEntity(any());
  }

  @Test
  @DisplayName("Test by Get users repository by username, there is not user")
  void findByUsernameEmpty() {

    when(this.userRepositoryJpa.findByEmailAndActiveIsTrue(anyString())).thenReturn(Optional.empty());

    final User result = this.repository.findByUsername("username");

    assertNull(result);
    verify(this.userRepositoryJpa).findByEmailAndActiveIsTrue(anyString());
    verify(this.userMapper, never()).toEntity(any());
  }

  @Test
  @DisplayName("findById: Test by Get users repository by id, user exists")
  void findById() {
    final UserJpa userJpa = this.userJpaData.get(1);
    final User user = this.userData.get(1);

    when(this.userRepositoryJpa.findById(anyLong())).thenReturn(Optional.of(userJpa));
    when(this.userMapper.toEntity(any())).thenReturn(user);

    final User result = this.repository.findById(1L);

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userRepositoryJpa).findById(anyLong());
    verify(this.userMapper).toEntity(any());
  }

  @Test
  @DisplayName("findByIdEmpty: Test by Get users repository by id, user not exists")
  void findByIdEmpty() {

    when(this.userRepositoryJpa.findById(anyLong())).thenReturn(Optional.empty());

    final User result = this.repository.findById(1L);

    assertNull(result);
    verify(this.userRepositoryJpa).findById(anyLong());
    verify(this.userMapper, never()).toEntity(any());
  }

  @Test
  @DisplayName("update: Test Update users, user exists")
  void update() {
    final UserJpa userJpa = this.userJpaData.get(1);
    final User user = this.userData.get(1);

    when(this.userRepositoryJpa.findById(anyLong())).thenReturn(Optional.of(userJpa));
    when(this.userRepositoryJpa.save(any())).thenReturn(userJpa);
    when(this.userMapper.toEntity(any())).thenReturn(user);
    when(this.userMapper.toModel(any())).thenReturn(userJpa);

    final User result = this.repository.update(user);

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userRepositoryJpa).findById(anyLong());
    verify(this.userRepositoryJpa).save(any());
    verify(this.userMapper).toModel(any());
    verify(this.userMapper).toEntity(any());
  }

  @Test
  @DisplayName("update: Test Update users, user not exists")
  void updateNotExists() {
    final User user = this.userData.get(1);

    when(this.userRepositoryJpa.findById(anyLong())).thenReturn(Optional.empty());

    final User result = this.repository.update(user);

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userRepositoryJpa).findById(anyLong());
    verify(this.userRepositoryJpa, never()).save(any());
    verify(this.userMapper, never()).toEntity(any());
    verify(this.userMapper, never()).toModel(any());
  }

  @Test
  @DisplayName("updatePassword: Test Update password, user exists")
  void updatePassword() {
    final UserJpa userJpa = this.userJpaData.get(1);
    final User user = this.userData.get(1);

    when(this.userRepositoryJpa.findById(anyLong())).thenReturn(Optional.of(userJpa));
    when(this.userRepositoryJpa.save(any())).thenReturn(userJpa);
    when(this.userMapper.toEntity(any())).thenReturn(user);

    final User result = this.repository.updatePassword("password",1L);

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userRepositoryJpa).findById(anyLong());
    verify(this.userRepositoryJpa).save(any());
    verify(this.userMapper).toEntity(any());
  }

  @Test
  @DisplayName("updatePassword: Test Update password, user not exists")
  void updatePasswordNotExists() {

    when(this.userRepositoryJpa.findById(anyLong())).thenReturn(Optional.empty());

    final User result = this.repository.updatePassword("password",1L);

    assertNull(result);
    verify(this.userRepositoryJpa).findById(anyLong());
    verify(this.userRepositoryJpa, never()).save(any());
    verify(this.userMapper, never()).toEntity(any());

  }

  @Test
  @DisplayName("create: Test Create user")
  void create() {
    final UserJpa userJpa = this.userJpaData.get(1);
    final User user = this.userData.get(1);

    when(this.userRepositoryJpa.save(any())).thenReturn(userJpa);
    when(this.userMapper.toEntity(any())).thenReturn(user);
    when(this.userMapper.toModel(any())).thenReturn(userJpa);

    final User result = this.repository.create(user);

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userRepositoryJpa).save(any());
    verify(this.userMapper).toModel(any());
    verify(this.userMapper).toEntity(any());
  }

  @Test
  @DisplayName("delete: Test delete users, user not exists")
  void deleteNotExists() {

    when(this.userRepositoryJpa.findById(anyLong())).thenReturn(Optional.empty());

    this.repository.delete(1L);

    verify(this.userRepositoryJpa).findById(anyLong());
    verify(this.userRepositoryJpa, never()).save(any());
  }

  @Test
  @DisplayName("delete: Test delete password, user exists")
  void delete() {
    final UserJpa userJpa = this.userJpaData.get(1);

    when(this.userRepositoryJpa.findById(anyLong())).thenReturn(Optional.of(userJpa));
    when(this.userRepositoryJpa.save(any())).thenReturn(userJpa);

    this.repository.delete(1L);

    verify(this.userRepositoryJpa).findById(anyLong());
    verify(this.userRepositoryJpa).save(any());
  }


}