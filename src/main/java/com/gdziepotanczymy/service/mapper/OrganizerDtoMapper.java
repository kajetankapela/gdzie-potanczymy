package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.Organizer;
import com.gdziepotanczymy.service.dto.OrganizerDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class OrganizerDtoMapper {
    @Transactional
    public OrganizerDto toDto(Organizer organizer) {
        return OrganizerDto.builder()
                .id(organizer.getId())
                .name(organizer.getName())
                .createdAt(organizer.getCreatedAt())
                .updatedAt(organizer.getUpdatedAt())
                .build();
    }
}
