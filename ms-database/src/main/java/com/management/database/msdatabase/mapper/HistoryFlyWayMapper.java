package com.management.database.msdatabase.mapper;

import com.management.database.msdatabase.domain.entity.HistoryFlyWay;
import com.management.database.msdatabase.models.jpa.HistoryFlyWayJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface HistoryFlyWayMapper {
    
    HistoryFlyWay toModel (HistoryFlyWayJpa entity);
    
    List<HistoryFlyWay> toModels (List<HistoryFlyWayJpa> entities);

}
