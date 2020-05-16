package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.Participant;
import com.gdziepotanczymy.service.dto.ParticipantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class ParticipantDtoMapper {
    private final AddressDtoMapper addressDtoMapper;

    @Transactional
    public ParticipantDto toDto(Participant participant) {
        return ParticipantDto.builder()
                .id(participant.getId())
                .name(participant.getName())
                .surname(participant.getSurname())
                .gender(participant.getGender())
                .login(participant.getLogin())
                .email(participant.getEmail())
                .phoneNumber(participant.getPhoneNumber())
                .createdAt(participant.getCreatedAt())
                .updatedAt(participant.getUpdatedAt())
                .addressDto(addressDtoMapper.toDto(participant.getAddress()))
                .build();
    }
}
