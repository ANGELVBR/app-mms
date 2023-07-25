package com.management.users.api.controller;

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
@RequestMapping("${app.base-path}/users")
@RequiredArgsConstructor
public class UserController {

  private final @NonNull FindAllUserUseCase findAllUserUseCase;

  private final @NonNull FindUserByIdUseCase findUserByIdUseCase;

  private final @NonNull FindUserByUserNameUseCase findUserByUserNameUseCase;

  
  @Operation(summary = "Get all users")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the users",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = @Content) })
  @GetMapping("/")
  public ResponseEntity<List<User>> getUsers() {

    List<User> users = this.findAllUserUseCase.findAllUser();

    return ResponseEntity.ok(users);
  }

  @Operation(summary = "Get users by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the user by id",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = @Content) })
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = findUserByIdUseCase.findUserById(id);
    if (user != null) {
      return ResponseEntity.ok(user);
    }
    return ResponseEntity.notFound().build();
  }

  @Operation(summary = "Get user by username")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the users by username",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = @Content) })
  @GetMapping("/username/")
  public ResponseEntity<User> getUserByUserName(@RequestParam String username) {
    User user = findUserByUserNameUseCase.findUserByUserName(username);
    if (user != null) {
      return ResponseEntity.ok(user);
    }
    return ResponseEntity.notFound().build();
  }

}
