package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.service.dto.EventDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class EventDtoMapper {
    @Transactional
    public EventDto toDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .description(event.getDescription())
                .addressId(event.getAddressId())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }
}
