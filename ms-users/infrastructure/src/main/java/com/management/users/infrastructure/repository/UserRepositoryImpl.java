package com.management.users.infrastructure.repository;

import com.management.users.domain.model.entity.User;
import com.management.users.domain.repository.UserRepository;
import com.management.users.infrastructure.entity.UserJpa;
import com.management.users.infrastructure.mapper.UserMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

  private final @NonNull UserRepositoryJpa userRepositoryJpa;
  private final @NonNull UserMapper userMapper;

  @Override
  public List<User> findAllUsers() {
    LOG.debug("[findAllUsers] Start findAllUsers");
    return this.userRepositoryJpa.findAll().stream()
        .map(this.userMapper::toEntity)
        .toList();
  }

  @Override
  public User findByUsername(String email) {
    LOG.debug("[findByUsername] Start findByUsername: email : '{}'", email);

    return this.userRepositoryJpa.findByEmailAndActiveIsTrue(email)
        .map(this.userMapper::toEntity)
        .orElse(null);
  }

  @Override
  public User findById(Long id) {
    LOG.debug("[findById] Start findById: id : '{}'", id);

    return this.userRepositoryJpa.findById(id)
        .map(this.userMapper::toEntity)
        .orElse(null);
  }

  @Override
  public User update(User user, Long id) {
    LOG.debug("[update] Start update: user : '{}', id : '{}'", user, id);

    Optional<UserJpa> userExits = this.userRepositoryJpa.findById(id);
    if (userExits.isPresent()) {
      UserJpa userJpa = userExits.get();

      UserJpa userToUpdate = this.userMapper.toModel(user);
      userToUpdate.setId(userJpa.getId());

      return this.userMapper.toEntity(this.userRepositoryJpa.save(userJpa));
    }

    LOG.warn("[update] user not found. id: '{}', ", id);
    return user;
  }

  @Override
  public User updatePassword(String password, Long id) {

    LOG.debug("[update] Start update password: user : '{}', id : '{}'", password, id);

    Optional<UserJpa> userExits = this.userRepositoryJpa.findById(id);
    if (userExits.isPresent()) {
      UserJpa userJpa = userExits.get();
      userJpa.setPassword(password);

      return this.userMapper.toEntity(this.userRepositoryJpa.save(userJpa));
    }

    LOG.warn("[update] user not found. id: '{}', ", id);
    return null;
  }

  @Override
  public User create(User user) {
    LOG.debug("[create] Start create: user : '{}'", user);


    UserJpa userToCreate = this.userMapper.toModel(user);
    return this.userMapper.toEntity(this.userRepositoryJpa.save(userToCreate));
  }


  @Override
  public void delete(Long id) {
    LOG.debug("[delete] Start delete user: id : '{}'", id);

    Optional<UserJpa> userExits = this.userRepositoryJpa.findById(id);
    if (userExits.isPresent()) {
      UserJpa userJpa = userExits.get();
      userJpa.setActive(false);

      this.userRepositoryJpa.save(userJpa);
    }

    LOG.error("[delete] user not found. id: '{}', ", id);
  }
}
