package com.management.users.application.usecase;

import com.management.users.domain.data.UserData;
import com.management.users.domain.model.dto.UserDto;
import com.management.users.domain.model.entity.User;
import com.management.users.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllUserUseCaseImplTest {

  @Mock
  UserService userService;

  @InjectMocks
  FindAllUserUseCaseImpl useCase;

  UserData userData;

  @BeforeEach
  void setUp() {
    this.userData = new UserData();
  }
  
  @Test
  @DisplayName("Test by Get all users use case")
  void findAllUser() {
    final List<User> userList = this.userData.getList();
 
    when(this.userService.findAllUsers()).thenReturn(userList);

    final List<User> result = this.useCase.findAllUser();

    assertNotNull(result);
    assertEquals(userList.size(), result.size());
    assertEquals(userList, result);
    verify(this.userService).findAllUsers();
  }

  @Test
  @DisplayName("Test by Get all users use case - there aren't users")
  void findAllUserEmpty() {

    when(this.userService.findAllUsers()).thenReturn(Collections.emptyList());

    final List<User> result = this.useCase.findAllUser();

    assertNotNull(result);
    assertEquals(0, result.size());
    assertEquals(Collections.emptyList(), result);
    verify(this.userService).findAllUsers();
  }
}