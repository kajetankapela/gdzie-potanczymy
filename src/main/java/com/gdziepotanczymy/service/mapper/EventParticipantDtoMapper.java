package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.EventParticipant;
import com.gdziepotanczymy.service.dto.EventParticipantDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class EventParticipantDtoMapper {
    @Transactional
    public EventParticipantDto toDto(EventParticipant eventParticipant) {
        return EventParticipantDto.builder()
                .id(eventParticipant.getId())
                .event(eventParticipant.getEvent())
                .participant(eventParticipant.getParticipant())
                .createdAt(eventParticipant.getCreatedAt())
                .updatedAt(eventParticipant.getUpdatedAt())
                .build();
    }
}
