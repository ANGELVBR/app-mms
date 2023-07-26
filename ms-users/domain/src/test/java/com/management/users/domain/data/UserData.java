package com.management.users.domain.data;

import com.management.users.domain.model.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class UserData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, User> dataMap;
  
  private final RoleData roles;

  public UserData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();
    
    this.roles = new RoleData();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final User item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private User generate(final Integer i) {
    final long id = this.keyGenerator.getAndIncrement();
    return User.builder()
            .id(id)
            .name("NAME_" + id)
            .email("EMAIL_" + id)
            .active(true)
            .createDate(new Date())
            .roles(List.of(this.roles.get(1), this.roles.get(2)))
            .build();
  }

  public User get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<User> getList() {

    final List<User> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
