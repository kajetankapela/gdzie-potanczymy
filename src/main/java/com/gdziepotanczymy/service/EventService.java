package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.repository.EventRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateEventDto;
import com.gdziepotanczymy.service.dto.EventDto;
import com.gdziepotanczymy.service.mapper.EventDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository repository;
    private final EventDtoMapper mapper;

    @Transactional
    public List<EventDto> getAllEvents() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventDto getEventById(Long id) throws NotFound {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public EventDto createEvent(CreateUpdateEventDto createUpdateEventDto) {
        Event event = Event.builder()
                .name(createUpdateEventDto.getName())
                .startDate(createUpdateEventDto.getStartDate())
                .endDate(createUpdateEventDto.getEndDate())
                .description(createUpdateEventDto.getDescription())
                .addressId(createUpdateEventDto.getAddressId())
                .createdAt(OffsetDateTime.now())
                .build();

        Event savedEvent = repository.save(event);

        return mapper.toDto(savedEvent);
    }

    @Transactional
    public EventDto updateEventById(Long id, CreateUpdateEventDto createUpdateEventDto) throws NotFound {
        Event existingEvent = repository.findById(id).orElseThrow(NotFound::new);

        existingEvent.setName(createUpdateEventDto.getName());
        existingEvent.setStartDate(createUpdateEventDto.getStartDate());
        existingEvent.setEndDate(createUpdateEventDto.getEndDate());
        existingEvent.setDescription(createUpdateEventDto.getDescription());
        existingEvent.setAddressId(createUpdateEventDto.getAddressId());
        existingEvent.setUpdatedAt(OffsetDateTime.now());

        Event savedEvent = repository.save(existingEvent);

        return mapper.toDto(savedEvent);
    }

    @Transactional
    public EventDto deleteEventById(Long id) throws NotFound {
        Event existingEvent = repository.findById(id).orElseThrow(NotFound::new);

        repository.delete(existingEvent);

        return mapper.toDto(existingEvent);
    }
}
