package com.management.users.api.data;

import com.management.api.user.dto.UserDto;

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
    final int id = this.keyGenerator.getAndIncrement();
    
    UserDto userDto = new UserDto();
    userDto.setId(id);
    userDto.setName("NAME_" + id);
    userDto.setEmail("EMAIL_" + id);
    userDto.setRoles(List.of(this.roles.get(1), this.roles.get(2)));
    
    return userDto;
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
