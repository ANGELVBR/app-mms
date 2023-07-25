package com.management.users.api.mapper;


import com.management.users.domain.mapper.GlobalMapperConfig;
import com.management.users.domain.model.dto.UserDto;
import com.management.users.domain.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
    config = GlobalMapperConfig.class
)
public interface RestUserMapper {

  UserDto toDto(User src);

  List<UserDto> toDtos(List<User> src);
  
  User toEntity(UserDto src);

}
