package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.EventOrganizer;
import com.gdziepotanczymy.service.dto.EventOrganizerDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class EventOrganizerDtoMapper {
    @Transactional
    public EventOrganizerDto toDto(EventOrganizer eventOrganizer) {
        return EventOrganizerDto.builder()
                .id(eventOrganizer.getId())
                .eventId(eventOrganizer.getEvent().getId())
                .organizerId(eventOrganizer.getOrganizer().getId())
                .createdAt(eventOrganizer.getCreatedAt())
                .updatedAt(eventOrganizer.getUpdatedAt())
                .build();
    }
}
