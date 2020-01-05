package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.EventStar;
import com.gdziepotanczymy.repository.EventRepository;
import com.gdziepotanczymy.repository.EventStarRepository;
import com.gdziepotanczymy.repository.StarRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateEventStarDto;
import com.gdziepotanczymy.service.dto.EventStarDto;
import com.gdziepotanczymy.service.mapper.EventStarDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventStarService {
    private final EventStarRepository eventStarRepository;
    private final EventStarDtoMapper mapper;
    private final EventRepository eventRepository;
    private  final StarRepository starRepository;

    @Transactional
    public List<EventStarDto> getAllEventStars() {
        return eventStarRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventStarDto getEventStarById(Long id) throws NotFound {
        return eventStarRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public EventStarDto createEventStar(CreateUpdateEventStarDto createUpdateEventStarDto) throws NotFound {
        EventStar eventStar = EventStar.builder()
                .event(eventRepository
                        .findById(createUpdateEventStarDto.getEventId())
                        .orElseThrow(NotFound::new))
                .star(starRepository
                        .findById(createUpdateEventStarDto.getStarId())
                        .orElseThrow(NotFound::new))
                .createdAt(OffsetDateTime.now())
                .build();

        EventStar savedEventStar = eventStarRepository.save(eventStar);

        return mapper.toDto(savedEventStar);
    }

    @Transactional
    public EventStarDto updateEventStarById(Long id, CreateUpdateEventStarDto createUpdateEventStarDto) throws NotFound {
        EventStar existingEventStar = eventStarRepository.findById(id).orElseThrow(NotFound::new);

        existingEventStar.setEvent(eventRepository
                .findById(createUpdateEventStarDto.getEventId())
                .orElseThrow(NotFound::new));
        existingEventStar.setStar(starRepository
                .findById(createUpdateEventStarDto.getStarId())
                .orElseThrow(NotFound::new));
        existingEventStar.setUpdatedAt(OffsetDateTime.now());

        EventStar savedEventStar = eventStarRepository.save(existingEventStar);

        return mapper.toDto(savedEventStar);
    }
}
