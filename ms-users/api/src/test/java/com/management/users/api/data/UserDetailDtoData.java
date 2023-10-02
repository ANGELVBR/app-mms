package com.management.users.api.data;

import com.management.api.user.dto.UserDetailDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class UserDetailDtoData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, UserDetailDto> dataMap;
  
  private final RoleDetailDtoData roles;

  public UserDetailDtoData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();
    
    this.roles = new RoleDetailDtoData();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final UserDetailDto item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private UserDetailDto generate(final Integer i) {
    final int id = this.keyGenerator.getAndIncrement();

    UserDetailDto userDto = new UserDetailDto();
    userDto.setId(id);
    userDto.setName("NAME_" + id);
    userDto.setEmail("EMAIL_" + id);
    userDto.setRoles(List.of(this.roles.get(1), this.roles.get(2)));
    
    return userDto;
  }

  public UserDetailDto get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<UserDetailDto> getList() {

    final List<UserDetailDto> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
