package com.management.database.msdatabase.mapper.rest;

import com.management.api.database.dto.HistoriesDto;
import com.management.api.database.dto.HistoryDto;
import com.management.database.msdatabase.domain.entity.HistoryFlyWay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {
        MappingUtils.class
    }
)
public interface RestHistoryFlyWayMapper {

    @Mapping(source = "installedOn", target = "installedOn", qualifiedByName = "dateToOffset")
    @Mapping(source = "installedRank", target = "id")
    HistoryDto toHistoryDto (HistoryFlyWay entity);

    @Mapping(source = "installedOn", target = "installedOn", qualifiedByName = "dateToOffset")
    @Mapping(source = "installedRank", target = "id")
    HistoriesDto toHistoriesDto (HistoryFlyWay entity);
    
    List<HistoriesDto> toHistoriesDto (List<HistoryFlyWay> entities);

}
