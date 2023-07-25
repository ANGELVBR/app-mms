package com.management.users.api.data;

import com.management.users.domain.data.RoleData;
import com.management.users.domain.model.dto.UserDto;
import com.management.users.domain.model.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class UserDtoData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, UserDto> dataMap;
  
  private final RoleDtoData roles;

  public UserDtoData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();
    
    this.roles = new RoleDtoData();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final UserDto item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private UserDto generate(final Integer i) {
    final long id = this.keyGenerator.getAndIncrement();
    return UserDto.builder()
            .id(id)
            .name("NAME_" + id)
            .email("EMAIL_" + id)
            .active(true)
            .createDate(new Date())
            .roles(List.of(this.roles.get(1), this.roles.get(2)))
            .build();
  }

  public UserDto get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<UserDto> getList() {

    final List<UserDto> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
