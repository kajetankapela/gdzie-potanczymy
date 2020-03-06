package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class EventDtoMapper {
    private final AddressDtoMapper addressDtoMapper;
    private final NumberOfSeatsDtoMapper numberOfSeatsDtoMapper;

    @Transactional
    public EventDto toDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .description(event.getDescription())
                .comments(event.getComments())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .address(addressDtoMapper.toDto(event.getAddress()))
                .numberOfSeats(numberOfSeatsDtoMapper.toDto(event.getNumberOfSeats()))
                .build();
    }
}
