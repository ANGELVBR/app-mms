package com.management.database.msdatabase.data;

import com.management.database.msdatabase.domain.entity.HistoryFlyWay;
import com.management.database.msdatabase.models.jpa.HistoryFlyWayJpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class HistoryFlyWayJpaData {

  private final AtomicInteger keyGenerator;

  private final Map<Integer, HistoryFlyWayJpa> dataMap;

  public HistoryFlyWayJpaData() {
    this.keyGenerator = new AtomicInteger(1);
    this.dataMap = new ConcurrentHashMap<>();

    IntStream.range(0, 5).forEach(i -> {
      final Integer key = this.keyGenerator.get();

      final HistoryFlyWayJpa item = this.generate(i);
      this.dataMap.put(key, item);
    });
  }

  private HistoryFlyWayJpa generate(final Integer i) {
    final int id = this.keyGenerator.getAndIncrement();
    HistoryFlyWayJpa historyFlyWay = new HistoryFlyWayJpa();

    historyFlyWay.setInstalledRank(id);
    historyFlyWay.setSuccess(Boolean.TRUE);
    historyFlyWay.setType("TYPE_" + id);
    historyFlyWay.setVersion("VERSION_" + id);
    historyFlyWay.setInstalledBy("INSTALLED_BY_" + id);
    historyFlyWay.setInstalledOn(new Date());
    historyFlyWay.setExecutionTime(id);
    historyFlyWay.setChecksum(id);
    historyFlyWay.setDescription("DESCRIPTION_" + id);
    
    return historyFlyWay;
  }

  public HistoryFlyWayJpa get(final Integer id) {
    return this.dataMap.get(id);
  }

  public List<HistoryFlyWayJpa> getList() {
    final List<HistoryFlyWayJpa> list = new ArrayList<>();
    this.dataMap.forEach((k, v) -> {
      list.add(v);
    });

    return list;
  }

}
