package com.management.users.api.controller;

import com.management.api.user.dto.UserDetailDto;
import com.management.api.user.dto.UserDto;
import com.management.api.user.dto.UsersDto;
import com.management.api.user.services.UsersApi;
import com.management.users.api.mapper.RestUserMapper;
import com.management.users.domain.model.entity.User;
import com.management.users.domain.usecase.FindAllUserUseCase;
import com.management.users.domain.usecase.FindUserByIdUseCase;
import com.management.users.domain.usecase.FindUserByUserNameUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

  private final @NonNull FindAllUserUseCase findAllUserUseCase;

  private final @NonNull FindUserByIdUseCase findUserByIdUseCase;

  private final @NonNull FindUserByUserNameUseCase findUserByUserNameUseCase;
  
  private final @NonNull RestUserMapper restUserMapper;

  @Override
  public ResponseEntity<UserDetailDto> getUserById(Integer userId) {
    User user = findUserByIdUseCase.findUserById(Long.valueOf(userId));
    if (user != null) {
      return ResponseEntity.ok(this.restUserMapper.toUserDto(user));
    }
    return ResponseEntity.notFound().build();
  }

  @Override
  public ResponseEntity<UserDetailDto> getUserByUserName(String userName) {
    User user = findUserByUserNameUseCase.findUserByUserName(userName);
    if (user != null) {
      return ResponseEntity.ok(this.restUserMapper.toUserDto(user));
    }
    return ResponseEntity.notFound().build();
  }


  @Override
  public ResponseEntity<List<UsersDto>> getUsers() {
    List<User> users = this.findAllUserUseCase.findAllUser();

    return ResponseEntity.ok(this.restUserMapper.toUsersDtos(users));
  }

}
