package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.EventParticipant;
import com.gdziepotanczymy.repository.EventParticipantRepository;
import com.gdziepotanczymy.repository.EventRepository;
import com.gdziepotanczymy.repository.ParticipantRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateEventParticipantDto;
import com.gdziepotanczymy.service.dto.EventParticipantDto;
import com.gdziepotanczymy.service.mapper.EventParticipantDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventParticipantService {
    private final EventParticipantRepository eventParticipantRepository;
    private final EventParticipantDtoMapper mapper;
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;

    @Transactional
    public List<EventParticipantDto> getAllEventParticipants() {
        return eventParticipantRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventParticipantDto getEventParticipantById(Long id) throws NotFound {
        return eventParticipantRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public EventParticipantDto createEventParticipant(CreateUpdateEventParticipantDto createUpdateEventParticipantDto) throws NotFound, BadRequest {
        if (createUpdateEventParticipantDto.getEventId() == null || createUpdateEventParticipantDto.getParticipantId() == null) {
            throw new BadRequest();
        }

        EventParticipant eventParticipant = EventParticipant.builder()
                .event(eventRepository
                        .findById(createUpdateEventParticipantDto.getEventId())
                        .orElseThrow(NotFound::new))
                .participant(participantRepository
                        .findById(createUpdateEventParticipantDto.getParticipantId())
                        .orElseThrow(NotFound::new))
                .createdAt(OffsetDateTime.now())
                .build();

        EventParticipant savedEventParticipant = eventParticipantRepository.save(eventParticipant);

        return mapper.toDto(savedEventParticipant);
    }

    @Transactional
    public EventParticipantDto updateEventParticipantById(Long id, CreateUpdateEventParticipantDto createUpdateEventParticipantDto) throws NotFound {
        EventParticipant existingEventParticipant = eventParticipantRepository.findById(id).orElseThrow(NotFound::new);

        existingEventParticipant.setEvent(eventRepository
                .findById(createUpdateEventParticipantDto.getEventId())
                .orElseThrow(NotFound::new));
        existingEventParticipant.setParticipant(participantRepository
                .findById(createUpdateEventParticipantDto.getParticipantId())
                .orElseThrow(NotFound::new));
        existingEventParticipant.setUpdatedAt(OffsetDateTime.now());

        EventParticipant savedEventParticipant = eventParticipantRepository.save(existingEventParticipant);

        return mapper.toDto(savedEventParticipant);
    }

    @Transactional
    public EventParticipantDto deleteEventParticipantById(Long id) throws NotFound {
        EventParticipant existingEventParticipant = eventParticipantRepository.findById(id).orElseThrow(NotFound::new);

        eventParticipantRepository.delete(existingEventParticipant);

        return mapper.toDto(existingEventParticipant);
    }
}
