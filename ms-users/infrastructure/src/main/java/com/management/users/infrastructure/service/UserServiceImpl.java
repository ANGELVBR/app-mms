package com.management.users.infrastructure.service;

import com.management.users.domain.model.entity.User;
import com.management.users.domain.repository.UserRepository;
import com.management.users.domain.service.UserService;
import com.management.users.infrastructure.repository.UserRepositoryImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

  private final @NonNull UserRepository userRepository;

  @Override
  public List<User> findAllUsers() {
    LOG.debug("[findAllUsers] Start findAllUsers");
    return this.userRepository.findAllUsers();
  }

  @Override
  public User findByUsername(String username) {
    LOG.debug("[findByUsername] Start findByUsername: username : '{}'", username);
    return this.userRepository.findByUsername(username);
  }

  @Override
  public User findById(Long id) {
    LOG.debug("[findById] Start findById: id : '{}'", id);
    return this.userRepository.findById(id);
  }

  @Override
  @Transactional
  public User update(User user) {
    LOG.debug("[update] Start update: User: '{}' : ", user);
    return this.userRepository.update(user);
  }

  @Override
  @Transactional
  public User updatePassword(String password, Long id) {
    LOG.debug("[update] Start update password by user id : '{}'", id);
    return this.userRepository.updatePassword(password, id);
  }

  @Override
  @Transactional
  public User create(User user) {
    LOG.debug("[create] Start create: User: '{}'", user);
    return this.userRepository.create(user);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    LOG.debug("[delete] Start delete: id : '{}'", id);
    this.userRepository.delete(id);
  }
}
