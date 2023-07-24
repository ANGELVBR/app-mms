package com.management.database.msdatabase.service;

import com.management.database.msdatabase.data.HistoryFlyWayData;
import com.management.database.msdatabase.data.HistoryFlyWayJpaData;
import com.management.database.msdatabase.domain.entity.HistoryFlyWay;
import com.management.database.msdatabase.mapper.HistoryFlyWayMapper;
import com.management.database.msdatabase.models.jpa.HistoryFlyWayJpa;
import com.management.database.msdatabase.models.repository.HistoryFlyWayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryFlyWayServiceImplTest {

    @Mock
    HistoryFlyWayRepository historyFlyWayRepository;

    @Mock
    HistoryFlyWayMapper historyFlyWayMapper;

    @InjectMocks
    HistoryFlyWayServiceImpl service;

    HistoryFlyWayData historyFlyWayData;

    HistoryFlyWayJpaData historyFlyWayJpaData;

    @BeforeEach
    void setUp() {
        this.historyFlyWayData = new HistoryFlyWayData();
        this.historyFlyWayJpaData = new HistoryFlyWayJpaData();
    }
    
    @Test
    void findAll() {

        final List<HistoryFlyWayJpa> historyFlyWayJpaList = this.historyFlyWayJpaData.getList();
        final List<HistoryFlyWay> historyFlyWaysList = this.historyFlyWayData.getList();


        when(this.historyFlyWayRepository.findAll()).thenReturn(historyFlyWayJpaList);
        when(this.historyFlyWayMapper.toModels(any())).thenReturn(historyFlyWaysList);

        final List<HistoryFlyWay> result = this.service.findAll();
        
        assertNotNull(result);
        assertEquals(historyFlyWayJpaList.size(), result.size());
        assertEquals(historyFlyWaysList.get(0), result.get(0));
        assertEquals(historyFlyWaysList.get(1), result.get(1));
                
        verify(this.historyFlyWayRepository).findAll();
        verify(this.historyFlyWayMapper).toModels(any());

    }

    @Test
    void findById() {

        final HistoryFlyWayJpa historyFlyWayJpa = this.historyFlyWayJpaData.get(1);
        final HistoryFlyWay historyFlyWay = this.historyFlyWayData.get(1);


        when(this.historyFlyWayRepository.findById(1)).thenReturn(Optional.of(historyFlyWayJpa));
        when(this.historyFlyWayMapper.toModel(any(HistoryFlyWayJpa.class))).thenReturn(historyFlyWay);

        final HistoryFlyWay result = this.service.findById(1);

        assertNotNull(result);
        assertEquals(historyFlyWay, result);
    
        verify(this.historyFlyWayRepository).findById(1);
        verify(this.historyFlyWayMapper).toModel(any(HistoryFlyWayJpa.class));
        
    }
}