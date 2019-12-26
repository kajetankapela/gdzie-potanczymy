package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.EventStar;
import com.gdziepotanczymy.service.dto.EventStarDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class EventStarDtoMapper {
    @Transactional
    public EventStarDto toDto(EventStar eventStar) {
        return EventStarDto.builder()
                .id(eventStar.getId())
                .event(eventStar.getEvent())
                .star(eventStar.getStar())
                .createdAt(eventStar.getCreatedAt())
                .updatedAt(eventStar.getUpdatedAt())
                .build();
    }
}
