package com.management.users.domain.data;

import com.management.users.domain.model.entity.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ProfileData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, Profile> dataMap;

  public ProfileData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final Profile item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private Profile generate(final Integer i) {
    final long id = this.keyGenerator.getAndIncrement();
    return Profile.builder()
            .id(id)
            .photo("CODE_" + id)
            .description("DESCRIPTION_" + id)
            .interest("DESCRIPTION_" + id)
            .build();
  }

  public Profile get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<Profile> getList() {

    final List<Profile> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
