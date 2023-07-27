package com.management.users.api.data;

import com.management.api.user.dto.UserDto;
import com.management.api.user.dto.UsersDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class UsersDtoData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, UsersDto> dataMap;
  

  public UsersDtoData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();
    
    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final UsersDto item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private UsersDto generate(final Integer i) {
    final int id = this.keyGenerator.getAndIncrement();

    UsersDto userDto = new UsersDto();
    userDto.setId(id);
    userDto.setName("NAME_" + id);
    userDto.setEmail("EMAIL_" + id);
    
    return userDto;
  }

  public UsersDto get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<UsersDto> getList() {

    final List<UsersDto> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
