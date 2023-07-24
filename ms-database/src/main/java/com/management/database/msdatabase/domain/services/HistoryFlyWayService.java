package com.management.database.msdatabase.domain.services;

import java.util.List;
import java.util.Optional;

import com.management.database.msdatabase.domain.entity.HistoryFlyWay;

public interface HistoryFlyWayService {

  List<HistoryFlyWay> findAll();
  
  HistoryFlyWay findById(Integer id);
  
}
