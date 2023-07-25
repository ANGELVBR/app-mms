package com.management.users.infrastructure.data;

import com.management.users.domain.data.RoleData;
import com.management.users.domain.model.entity.User;
import com.management.users.infrastructure.entity.UserJpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class UserJpaData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, UserJpa> dataMap;
  
  private final RoleJpaData roles;

  public UserJpaData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();
    
    this.roles = new RoleJpaData();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final UserJpa item = this.generate();
      this.dataMap.put(key, item);
    });
  }

  private UserJpa generate() {
    final long id = this.keyGenerator.getAndIncrement();
    UserJpa userJpa = new UserJpa();
    userJpa.setId(id);
    userJpa.setName("NAME_" + id);
    userJpa.setEmail("EMAIL_" + id);
    userJpa.setActive(true);
    userJpa.setCreateDate(new Date());
    userJpa.setRoles(List.of(this.roles.get(1), this.roles.get(2)));
    return userJpa;
  }

  public UserJpa get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<UserJpa> getList() {

    final List<UserJpa> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
