package com.management.users.api.data;

import com.management.api.user.dto.RoleDetailDto;
import com.management.api.user.dto.RoleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class RoleDetailDtoData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, RoleDetailDto> dataMap;

  public RoleDetailDtoData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final RoleDetailDto item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private RoleDetailDto generate(final Integer i) {
    final int id = this.keyGenerator.getAndIncrement();
    RoleDetailDto roleDto = new RoleDetailDto();
    roleDto.setCode("CODE_" + id);
    roleDto.setDescription("DESCRIPTION_" + id);
    return roleDto;
  }

  public RoleDetailDto get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<RoleDetailDto> getList() {

    final List<RoleDetailDto> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
