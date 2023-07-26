package com.management.users.api.controller;

import com.management.users.api.data.UserDtoData;
import com.management.users.api.mapper.RestUserMapper;
import com.management.users.domain.data.UserData;
import com.management.users.domain.model.dto.UserDto;
import com.management.users.domain.model.entity.User;
import com.management.users.domain.usecase.FindAllUserUseCase;
import com.management.users.domain.usecase.FindUserByIdUseCase;
import com.management.users.domain.usecase.FindUserByUserNameUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  FindAllUserUseCase findAllUserUseCase;

  @Mock
  FindUserByIdUseCase findUserByIdUseCase;

  @Mock
  FindUserByUserNameUseCase findUserByUserNameUseCase;

  @Mock
  RestUserMapper restUserMapper;

  @InjectMocks
  UserController controller;

  UserData userData;
  UserDtoData userDtoData;

  @BeforeEach
  void setUp() {

    this.userData = new UserData();
    this.userDtoData = new UserDtoData();
  }
  
  @Test
  @DisplayName("Test by Get all users - there are users")
  void getUsers() {
    final List<User> userList = this.userData.getList();
    final List<UserDto> userDtoList = this.userDtoData.getList();

    when(this.findAllUserUseCase.findAllUser()).thenReturn(userList);
    when(this.restUserMapper.toDtos(anyList())).thenReturn(userDtoList);

    final ResponseEntity<List<UserDto>> result = this.controller.getUsers();
    
    assertNotNull(result);
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertNotNull(result.getBody());
    assertEquals(userList.size(), result.getBody().size());
    assertEquals(userDtoList, result.getBody());
    
    verify(this.findAllUserUseCase).findAllUser();
    verify(this.restUserMapper).toDtos(anyList());

  }

  @Test
  @DisplayName("Test by Get all users - there are no users")
  void getUsersEmpty() {
    when(this.findAllUserUseCase.findAllUser()).thenReturn(Collections.emptyList());
    when(this.restUserMapper.toDtos(anyList())).thenReturn(Collections.emptyList());

    final ResponseEntity<List<UserDto>> result = this.controller.getUsers();

    assertNotNull(result);
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertNotNull(result.getBody());
    assertEquals(0, result.getBody().size());
    assertEquals(Collections.emptyList(), result.getBody());

    verify(this.findAllUserUseCase).findAllUser();
    verify(this.restUserMapper).toDtos(anyList());

  }

  @Test
  @DisplayName("Test by Get users by id - there is user")
  void getUserById() {
    final User user = this.userData.get(1);
    final UserDto userDto = this.userDtoData.get(1);

    when(this.findUserByIdUseCase.findUserById(anyLong())).thenReturn(user);
    when(this.restUserMapper.toDto(any())).thenReturn(userDto);

    final ResponseEntity<UserDto> result = this.controller.getUserById(1L);

    assertNotNull(result);
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertNotNull(result.getBody());
    assertEquals(userDto, result.getBody());

    verify(this.findUserByIdUseCase).findUserById(anyLong());
    verify(this.restUserMapper).toDto(any());
    
  }

  @Test
  @DisplayName("Test by Get users by id - there isn´t user")
  void getUserByIdEmpty() {
    when(this.findUserByIdUseCase.findUserById(anyLong())).thenReturn(null);

    final ResponseEntity<UserDto> result = this.controller.getUserById(1L);

    assertNotNull(result);
    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    assertNull(result.getBody());

    verify(this.findUserByIdUseCase).findUserById(anyLong());
    verify(this.restUserMapper, never()).toDto(any());
  }

  @Test
  @DisplayName("Test by Get users by username - there is user")
  void getUserByUserName() {
    final User user = this.userData.get(1);
    final UserDto userDto = this.userDtoData.get(1);

    when(this.findUserByUserNameUseCase.findUserByUserName(anyString())).thenReturn(user);
    when(this.restUserMapper.toDto(any())).thenReturn(userDto);

    final ResponseEntity<UserDto> result = this.controller.getUserByUserName("username");

    assertNotNull(result);
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertNotNull(result.getBody());
    assertEquals(userDto, result.getBody());

    verify(this.findUserByUserNameUseCase).findUserByUserName(anyString());
    verify(this.restUserMapper).toDto(any());
  }

  @Test
  @DisplayName("Test by Get users by username - there isn´t user")
  void getUserByUserNameEmpty() {

    when(this.findUserByUserNameUseCase.findUserByUserName(anyString())).thenReturn(null);

    final ResponseEntity<UserDto> result = this.controller.getUserByUserName("username");

    assertNotNull(result);
    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    assertNull(result.getBody());

    verify(this.findUserByUserNameUseCase).findUserByUserName(anyString());
    verify(this.restUserMapper, never()).toDto(any());
  }
}