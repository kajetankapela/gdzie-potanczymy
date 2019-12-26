package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.Participant;
import com.gdziepotanczymy.service.dto.ParticipantDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ParticipantDtoMapper {
    @Transactional
    public ParticipantDto toDto(Participant participant) {
        return ParticipantDto.builder()
                .id(participant.getId())
                .name(participant.getName())
                .surname(participant.getSurname())
                .createdAt(participant.getCreatedAt())
                .updatedAt(participant.getUpdatedAt())
                .build();
    }
}
