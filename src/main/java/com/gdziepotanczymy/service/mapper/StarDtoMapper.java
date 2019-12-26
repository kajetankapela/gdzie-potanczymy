package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.Star;
import com.gdziepotanczymy.service.dto.StarDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class StarDtoMapper {
    @Transactional
    public StarDto toDto(Star star) {
        return StarDto.builder()
                .id(star.getId())
                .name(star.getName())
                .createdAt(star.getCreatedAt())
                .updatedAt(star.getUpdatedAt())
                .build();
    }
}
