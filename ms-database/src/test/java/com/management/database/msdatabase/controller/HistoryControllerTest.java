package com.management.database.msdatabase.controller;

import com.management.api.database.dto.HistoriesDto;
import com.management.api.database.dto.HistoryDto;
import com.management.database.msdatabase.data.HistoryFlyWayData;
import com.management.database.msdatabase.domain.entity.HistoryFlyWay;
import com.management.database.msdatabase.domain.services.HistoryFlyWayService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryControllerTest {

    @Mock
    HistoryFlyWayService historyFlyWayService;

    @InjectMocks
    HistoryController controller;

    HistoryFlyWayData historyFlyWayData;

    @BeforeEach
    void setUp() {
        this.historyFlyWayData = new HistoryFlyWayData();
    }
    
    @Test
    void getHistorical() {

        final List<HistoryFlyWay> historyFlyWaysList = this.historyFlyWayData.getList();

        when(this.historyFlyWayService.findAll()).thenReturn(historyFlyWaysList);

        final ResponseEntity<List<HistoriesDto>> result = this.controller.getHistorical();

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(historyFlyWaysList.size(), result.getBody().size());

        verify(this.historyFlyWayService).findAll();
    }

    @Test
    void getHistoricalById() {
        final HistoryFlyWay historyFlyWays = this.historyFlyWayData.get(1);

        when(this.historyFlyWayService.findById(anyInt())).thenReturn(historyFlyWays);

        final ResponseEntity<HistoryDto> result = this.controller.getHistoricalById(1);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(historyFlyWays.getInstalledRank(), result.getBody().getId());

        verify(this.historyFlyWayService).findById(anyInt());
    }

    @Test
    void getHistoricalById_noContent() {

        when(this.historyFlyWayService.findById(anyInt())).thenReturn(null);

        final ResponseEntity<HistoryDto> result = this.controller.getHistoricalById(1);

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());

        verify(this.historyFlyWayService).findById(anyInt());
    }
}