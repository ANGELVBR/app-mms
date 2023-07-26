package com.management.users.infrastructure.data;

import com.management.users.domain.model.entity.Role;
import com.management.users.infrastructure.entity.RoleJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class RoleJpaData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, RoleJpa> dataMap;

  public RoleJpaData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final RoleJpa item = this.generate();
      this.dataMap.put(key, item);
    });
  }

  private RoleJpa generate() {
    final Integer id = this.keyGenerator.getAndIncrement();
    RoleJpa roleJpa = new RoleJpa(); 
    roleJpa.setId(id);    
    roleJpa.setCode("CODE_" + id);
    roleJpa.setDescription("DESCRIPTION_" + id);
    return roleJpa;
  }

  public RoleJpa get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<RoleJpa> getList() {

    final List<RoleJpa> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
