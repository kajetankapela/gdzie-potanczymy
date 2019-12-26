package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.User;
import com.gdziepotanczymy.service.dto.UserDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UserDtoMapper {
    @Transactional
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
