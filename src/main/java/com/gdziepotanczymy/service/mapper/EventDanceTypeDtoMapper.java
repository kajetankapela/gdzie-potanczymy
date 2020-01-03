package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.EventDanceType;
import com.gdziepotanczymy.service.dto.EventDanceTypeDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class EventDanceTypeDtoMapper {
    @Transactional
    public EventDanceTypeDto toDto(EventDanceType eventDanceType) {
        return EventDanceTypeDto.builder()
                .id(eventDanceType.getId())
                .eventId(eventDanceType.getEvent().getId())
                .danceTypeId(eventDanceType.getDanceType().getId())
                .createdAt(eventDanceType.getCreatedAt())
                .updatedAt(eventDanceType.getUpdatedAt())
                .build();
    }
}
