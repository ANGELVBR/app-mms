package com.management.users.infrastructure.mapper;

import com.management.users.domain.mapper.GlobalMapperConfig;
import com.management.users.domain.model.entity.Role;
import com.management.users.infrastructure.entity.RoleJpa;
import org.mapstruct.Mapper;

@Mapper(
    config = GlobalMapperConfig.class
)
public interface RoleMapper {

  Role toEntity(RoleJpa src);

  RoleJpa toModel(Role src);

}
