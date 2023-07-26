package com.management.users.application.usecase;

import com.management.users.domain.data.UserData;
import com.management.users.domain.model.entity.User;
import com.management.users.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindUserByUserNameUseCaseImplTest {

  @Mock
  UserService userService;

  @InjectMocks
  FindUserByUserNameUseCaseImpl useCase;

  UserData userData;

  @BeforeEach
  void setUp() {
    this.userData = new UserData();
  }
  
  @Test
  @DisplayName("Test by Get user by username use case")
  void findUserByUserName() {
    final User user = this.userData.get(1);

    when(this.userService.findByUsername(anyString())).thenReturn(user);

    final User result = this.useCase.findUserByUserName("username");

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userService).findByUsername(anyString());
  }

  @Test
  @DisplayName("Test by Get user by username use case, there isn't user")
  void findUserByUserNameEmpty() {
 
    when(this.userService.findByUsername(anyString())).thenReturn(null);

    final User result = this.useCase.findUserByUserName("username");

    assertNull(result);
    verify(this.userService).findByUsername(anyString());
  }
}