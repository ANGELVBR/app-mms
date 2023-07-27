package com.management.users.api.mapper;


import com.management.api.user.dto.RoleDto;
import com.management.users.domain.mapper.GlobalMapperConfig;
import com.management.users.domain.model.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
    config = GlobalMapperConfig.class
)
public interface RestRoleMapper {

  RoleDto toDto(Role src);

  List<RoleDto> toDtos(List<Role> src);
 

}
