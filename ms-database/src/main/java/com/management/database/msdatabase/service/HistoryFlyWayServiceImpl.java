package com.management.database.msdatabase.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.management.database.msdatabase.domain.entity.HistoryFlyWay;
import com.management.database.msdatabase.domain.services.HistoryFlyWayService;
import com.management.database.msdatabase.mapper.HistoryFlyWayMapper;
import com.management.database.msdatabase.models.jpa.HistoryFlyWayJpa;
import com.management.database.msdatabase.models.repository.HistoryFlyWayRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistoryFlyWayServiceImpl implements HistoryFlyWayService {

  private final @NonNull HistoryFlyWayRepository historyFlyWayRepository;
  
  private final @NonNull HistoryFlyWayMapper historyFlyWayMapper;

  @Override
  @Transactional(readOnly = true)
  public List<HistoryFlyWay> findAll() {

    final List<HistoryFlyWayJpa> historyFlyWayJpas = this.historyFlyWayRepository.findAll();

    return this.historyFlyWayMapper.toModels(historyFlyWayJpas);

  }

  @Override
  @Transactional(readOnly = true)
  public HistoryFlyWay findById(Integer id) {
    
    return this.historyFlyWayRepository.findById(id).map(historyFlyWayMapper::toModel).orElse(null);
    
  }
}
