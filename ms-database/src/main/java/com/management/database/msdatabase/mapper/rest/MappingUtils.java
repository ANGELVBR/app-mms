package com.management.database.msdatabase.mapper.rest;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MappingUtils {

  @Named("dateToOffset")
  default OffsetDateTime dateToOffset(Date date) {
    if (date != null) {
      date = new Date();
      final LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
    } else {
      return null;
    }
  }
  
}
