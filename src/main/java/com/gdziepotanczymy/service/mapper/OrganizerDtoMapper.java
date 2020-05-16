package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.Organizer;
import com.gdziepotanczymy.service.dto.AddressDto;
import com.gdziepotanczymy.service.dto.OrganizerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class OrganizerDtoMapper {
    private final AddressDtoMapper addressDtoMapper;

    @Transactional
    public OrganizerDto toDto(Organizer organizer) {
        AddressDto addressDto = addressDtoMapper.toDto(organizer.getAddress());

        return OrganizerDto.builder()
                .id(organizer.getId())
                .name(organizer.getName())
                .login(organizer.getLogin())
                .email(organizer.getEmail())
                .phoneNumber(organizer.getPhoneNumber())
                .createdAt(organizer.getCreatedAt())
                .updatedAt(organizer.getUpdatedAt())
                .addressDto(addressDto)
                .build();
    }
}
