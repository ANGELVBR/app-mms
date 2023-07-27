package com.management.database.msdatabase.controller;

import com.management.api.database.dto.HistoriesDto;
import com.management.api.database.dto.HistoryDto;
import com.management.api.database.services.HistoryApi;
import com.management.database.msdatabase.domain.entity.HistoryFlyWay;
import com.management.database.msdatabase.domain.services.HistoryFlyWayService;
import com.management.database.msdatabase.mapper.rest.RestHistoryFlyWayMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HistoryController implements HistoryApi {
    
    private final @NonNull HistoryFlyWayService historyFlyWayService;
    private final @NonNull RestHistoryFlyWayMapper mapper;

    @Override
    public ResponseEntity<List<HistoriesDto>> getHistorical() {
        final List<HistoryFlyWay> historyFlyWays = this.historyFlyWayService.findAll();

        return ResponseEntity.ok(this.mapper.toHistoriesDto(historyFlyWays));
    }

    @Override
    public ResponseEntity<HistoryDto> getHistoricalById(Integer historyId) {
        final HistoryFlyWay historyFlyWay = historyFlyWayService.findById(historyId);
        if(historyFlyWay != null){
            return ResponseEntity.ok(this.mapper.toHistoryDto(historyFlyWay));
        }
        return ResponseEntity.notFound().build();
    }

   
}
