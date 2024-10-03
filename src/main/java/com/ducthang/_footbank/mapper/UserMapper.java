package com.ducthang._footbank.mapper;

import com.ducthang._footbank.dto.UserDTO;
import com.ducthang._footbank.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Allows for dependency injection
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);
}
