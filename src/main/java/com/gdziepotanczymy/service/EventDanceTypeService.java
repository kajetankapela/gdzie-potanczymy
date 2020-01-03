package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.EventDanceType;
import com.gdziepotanczymy.repository.DanceTypeRepository;
import com.gdziepotanczymy.repository.EventDanceTypeRepository;
import com.gdziepotanczymy.repository.EventRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateEventDanceTypeDto;
import com.gdziepotanczymy.service.dto.EventDanceTypeDto;
import com.gdziepotanczymy.service.mapper.EventDanceTypeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventDanceTypeService {
    private final EventDanceTypeRepository eventDanceTypeRepository;
    private final EventDanceTypeDtoMapper mapper;
    private final EventRepository eventRepository;
    private final DanceTypeRepository danceTypeRepository;

    @Transactional
    public List<EventDanceTypeDto> getAllEventDanceTypes() {
        return eventDanceTypeRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventDanceTypeDto getEventDanceTypeById(Long id) throws NotFound {
        return eventDanceTypeRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public EventDanceTypeDto createEventDanceType(CreateUpdateEventDanceTypeDto createUpdateEventDanceTypeDto) throws NotFound {
        EventDanceType eventDanceType = EventDanceType.builder()
                .event(eventRepository
                        .findById(createUpdateEventDanceTypeDto.getEventId())
                        .orElseThrow(NotFound::new))
                .danceType(danceTypeRepository
                        .findById(createUpdateEventDanceTypeDto.getDanceTypeId())
                        .orElseThrow(NotFound::new))
                .createdAt(OffsetDateTime.now())
                .build();

        EventDanceType savedEventDanceType = eventDanceTypeRepository.save(eventDanceType);

        return mapper.toDto(savedEventDanceType);
    }
}
