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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindUserByIdUseCaseImplTest {

  @Mock
  UserService userService;

  @InjectMocks
  FindUserByIdUseCaseImpl useCase;

  UserData userData;

  @BeforeEach
  void setUp() {
    this.userData = new UserData();
  }
  
  @Test
  @DisplayName("Test by Get user by Id use case")
  void findUserById() {
    final User user = this.userData.get(1);

    when(this.userService.findById(anyLong())).thenReturn(user);

    final User result = this.useCase.findUserById(1L);

    assertNotNull(result);
    assertEquals(user, result);
    verify(this.userService).findById(anyLong());
  }

  @Test
  @DisplayName("Test by Get user by Id use case, there isn't user")
  void findUserByIdEmpty() {

    when(this.userService.findById(anyLong())).thenReturn(null);

    final User result = this.useCase.findUserById(1L);

    assertNull(result);
    verify(this.userService).findById(anyLong());
  }
  
  
}