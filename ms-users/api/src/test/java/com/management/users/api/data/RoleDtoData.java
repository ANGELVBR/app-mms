package com.management.users.api.data;

import com.management.users.domain.model.dto.RoleDto;
import com.management.users.domain.model.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class RoleDtoData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, RoleDto> dataMap;

  public RoleDtoData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final RoleDto item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private RoleDto generate(final Integer i) {
    final Integer id = this.keyGenerator.getAndIncrement();
    return RoleDto.builder()
            .id(id)
            .code("CODE_" + id)
            .description("DESCRIPTION_" + id)
            .build();
  }

  public RoleDto get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<RoleDto> getList() {

    final List<RoleDto> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
