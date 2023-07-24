package com.management.database.msdatabase.data;

import com.management.database.msdatabase.domain.entity.HistoryFlyWay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class HistoryFlyWayData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, HistoryFlyWay> dataMap;

  public HistoryFlyWayData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final HistoryFlyWay item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private HistoryFlyWay generate(final Integer i) {
    final int id = this.keyGenerator.getAndIncrement();
    return HistoryFlyWay.builder()
        .installedRank(id)
            .script("SCRIPT_" + id)
            .success(Boolean.TRUE)
            .type("TYPE_" + id)
            .version("VERSION_" + id)
            .installedBy("INSTALLED_BY_" + id)
            .installedOn(new Date())
            .executionTime(id)
            .checksum(id)
        .description("DESCRIPTION_" + id)
        .build();
  }

  public HistoryFlyWay get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<HistoryFlyWay> getList() {

    final List<HistoryFlyWay> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
    
  }

}
