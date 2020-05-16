package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.service.dto.EventDto;
import com.gdziepotanczymy.service.dto.OrganizerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class EventDtoMapper {
    private final AddressDtoMapper addressDtoMapper;
    private final NumberOfSeatsDtoMapper numberOfSeatsDtoMapper;
    private final OrganizerDtoMapper organizerDtoMapper;

    @Transactional
    public EventDto toDto(Event event) {
        OrganizerDto organizerDto = organizerDtoMapper.toDto(event.getOrganizer());

        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .organizer(organizerDto)
                .description(event.getDescription())
                .comments(event.getComments())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .address(addressDtoMapper.toDto(event.getAddress()))
                .numberOfSeats(numberOfSeatsDtoMapper.toDto(event.getNumberOfSeats()))
                .build();
    }
}
