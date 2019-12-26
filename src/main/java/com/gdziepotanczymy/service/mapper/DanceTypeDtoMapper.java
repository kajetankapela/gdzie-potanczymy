package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.DanceType;
import com.gdziepotanczymy.service.dto.DanceTypeDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DanceTypeDtoMapper {
    @Transactional
    public DanceTypeDto toDto(DanceType danceType) {
        return DanceTypeDto.builder()
                .id(danceType.getId())
                .name(danceType.getName())
                .createdAt(danceType.getCreatedAt())
                .updatedAt(danceType.getUpdatedAt())
                .build();
    }
}
