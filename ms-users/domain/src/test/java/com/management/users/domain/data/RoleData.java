package com.management.users.domain.data;

import com.management.users.domain.model.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class RoleData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, Role> dataMap;

  public RoleData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final Role item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private Role generate(final Integer i) {
    final Integer id = this.keyGenerator.getAndIncrement();
    return Role.builder()
            .id(id)
            .code("CODE_" + id)
            .description("DESCRIPTION_" + id)
            .build();
  }

  public Role get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<Role> getList() {

    final List<Role> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
