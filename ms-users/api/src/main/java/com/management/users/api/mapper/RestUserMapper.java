package com.management.users.api.mapper;


import com.management.api.user.dto.UserDto;
import com.management.api.user.dto.UsersDto;
import com.management.users.api.mapper.utils.MappingUtils;
import com.management.users.domain.mapper.GlobalMapperConfig;
import com.management.users.domain.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {
        RestRoleMapper.class, 
        MappingUtils.class
    }
)
public interface RestUserMapper {

  @Mapping(source = "createDate", target = "createDate", qualifiedByName = "dateToOffset")
  UserDto toUserDto(User src);

  UsersDto toUsersDto(User src);

  List<UsersDto> toUsersDtos(List<User> src);

}
