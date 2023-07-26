package com.management.users.api.mapper.utils;

import com.management.users.domain.mapper.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.*;
import java.util.Date;

@Mapper(config = GlobalMapperConfig.class)
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
