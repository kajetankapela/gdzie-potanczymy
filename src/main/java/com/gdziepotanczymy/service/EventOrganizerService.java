package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.EventOrganizer;
import com.gdziepotanczymy.repository.EventOrganizerRepository;
import com.gdziepotanczymy.repository.EventRepository;
import com.gdziepotanczymy.repository.OrganizerRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateEventOrganizerDto;
import com.gdziepotanczymy.service.dto.EventOrganizerDto;
import com.gdziepotanczymy.service.mapper.EventOrganizerDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventOrganizerService {
    private final EventOrganizerRepository eventOrganizerRepository;
    private final EventOrganizerDtoMapper mapper;
    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;

    @Transactional
    public List<EventOrganizerDto> getAllEventOrganizers() {
        return eventOrganizerRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventOrganizerDto getEventOrganizerById(Long id) throws NotFound {
        return eventOrganizerRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public EventOrganizerDto createEventOrganizer(CreateUpdateEventOrganizerDto createUpdateEventOrganizerDto) throws NotFound {
        EventOrganizer eventOrganizer = EventOrganizer.builder()
                .event(eventRepository
                        .findById(createUpdateEventOrganizerDto.getEventId())
                        .orElseThrow(NotFound::new))
                .organizer(organizerRepository
                        .findById(createUpdateEventOrganizerDto.getOrganizerId())
                        .orElseThrow(NotFound::new))
                .createdAt(OffsetDateTime.now())
                .build();

        EventOrganizer savedEventOrganizer = eventOrganizerRepository.save(eventOrganizer);

        return mapper.toDto(savedEventOrganizer);
    }
}
