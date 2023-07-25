package com.management.users.infrastructure.mapper;

import com.management.users.domain.mapper.GlobalMapperConfig;
import com.management.users.domain.model.entity.User;
import com.management.users.infrastructure.entity.UserJpa;
import org.mapstruct.Mapper;

@Mapper(
    config = GlobalMapperConfig.class,
    uses = {RoleMapper.class}
)
public interface UserMapper {

  User toEntity(UserJpa src);

  UserJpa toModel(User src);

}
