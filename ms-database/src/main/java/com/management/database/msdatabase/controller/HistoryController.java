package com.management.database.msdatabase.controller;

import com.management.database.msdatabase.domain.entity.HistoryFlyWay;
import com.management.database.msdatabase.domain.services.HistoryFlyWayService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.base-path}")
@RequiredArgsConstructor
public class HistoryController {
    
    private final @NonNull HistoryFlyWayService historyFlyWayService;

    @GetMapping("/histories")
    public ResponseEntity<List<HistoryFlyWay>> getHistorical(){

        final List<HistoryFlyWay> historyFlyWays = this.historyFlyWayService.findAll();

        return ResponseEntity.ok(historyFlyWays);
    }

    @GetMapping("/histories/{id}")
    public ResponseEntity<HistoryFlyWay> getHistoricalById(@PathVariable Integer id){
        final HistoryFlyWay historyFlyWay = historyFlyWayService.findById(id);
        if(historyFlyWay != null){
            return ResponseEntity.ok(historyFlyWay);
        }
        return ResponseEntity.notFound().build();
    }
    
}
