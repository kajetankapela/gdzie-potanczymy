package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.*;
import com.gdziepotanczymy.repository.EventRepository;
import com.gdziepotanczymy.repository.OrganizerRepository;
import com.gdziepotanczymy.repository.ParticipantRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateEventDto;
import com.gdziepotanczymy.service.dto.EventDto;
import com.gdziepotanczymy.service.dto.ParticipantDto;
import com.gdziepotanczymy.service.mapper.EventDtoMapper;
import com.gdziepotanczymy.service.mapper.ParticipantDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventDtoMapper eventDtoMapper;
    private final OrganizerRepository organizerRepository;
    private final ParticipantRepository participantRepository;
    private final ParticipantDtoMapper participantDtoMapper;

    @Transactional
    public List<EventDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventDto getEventById(Long id) throws NotFound {
        return eventRepository.findById(id)
                .map(eventDtoMapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public EventDto createEvent(CreateUpdateEventDto createUpdateEventDto) throws BadRequest, NotFound {

        Address address = Address.builder()
                .country(createUpdateEventDto.getCreateUpdateAddressDto().getCountry())
                .postalCode(createUpdateEventDto.getCreateUpdateAddressDto().getPostalCode())
                .city(createUpdateEventDto.getCreateUpdateAddressDto().getCity())
                .street(createUpdateEventDto.getCreateUpdateAddressDto().getStreet())
                .number(createUpdateEventDto.getCreateUpdateAddressDto().getNumber())
                .build();

        NumberOfSeats numberOfSeats = NumberOfSeats.builder()
                .allSeats(createUpdateEventDto.getCreateUpdateNumberOfSeatsDto().getAllSeats())
                .freeSeats(createUpdateEventDto.getCreateUpdateNumberOfSeatsDto().getAllSeats())
                .unconfirmedSeats(0)
                .confirmedSeats(0)
                .build();

        Organizer organizer = organizerRepository.findByLogin(createUpdateEventDto.getOrganizerLogin());

        Event event = Event.builder()
                .name(createUpdateEventDto.getName())
                .startDate("" + createUpdateEventDto.getStartDay() + "/"
                        + createUpdateEventDto.getStartMonth() + "/" + createUpdateEventDto.getStartYear())
                .endDate("" + createUpdateEventDto.getEndDay() + "/"
                        + createUpdateEventDto.getEndMonth() + "/" + createUpdateEventDto.getEndYear())
                .organizer(organizer)
                .description(createUpdateEventDto.getDescription())
                .comments(createUpdateEventDto.getComments())
                .createdAt(OffsetDateTime.now())
                .address(address)
                .numberOfSeats(numberOfSeats)
                .build();

        Event savedEvent = eventRepository.save(event);

        return eventDtoMapper.toDto(savedEvent);
    }



    @Transactional
    public EventDto updateEventById(Long id, CreateUpdateEventDto createUpdateEventDto) throws NotFound, BadRequest {

        Address address = Address.builder()
                .country(createUpdateEventDto.getCreateUpdateAddressDto().getCountry())
                .postalCode(createUpdateEventDto.getCreateUpdateAddressDto().getPostalCode())
                .city(createUpdateEventDto.getCreateUpdateAddressDto().getCity())
                .street(createUpdateEventDto.getCreateUpdateAddressDto().getStreet())
                .number(createUpdateEventDto.getCreateUpdateAddressDto().getNumber())
                .build();

        NumberOfSeats numberOfSeats = NumberOfSeats.builder()
                .allSeats(createUpdateEventDto.getCreateUpdateNumberOfSeatsDto().getAllSeats())
                .freeSeats(createUpdateEventDto.getCreateUpdateNumberOfSeatsDto().getAllSeats())
                .unconfirmedSeats(0)
                .confirmedSeats(0)
                .build();

        Event existingEvent = eventRepository.findById(id).orElseThrow(NotFound::new);

        existingEvent.setName(createUpdateEventDto.getName());
        existingEvent.setStartDate("" + createUpdateEventDto.getStartDay() + "/"
                + createUpdateEventDto.getStartMonth() + "/" + createUpdateEventDto.getStartYear());
        existingEvent.setEndDate("" + createUpdateEventDto.getEndDay() + "/"
                + createUpdateEventDto.getEndMonth() + "/" + createUpdateEventDto.getEndYear());
        existingEvent.setDescription(createUpdateEventDto.getDescription());
        existingEvent.setComments(createUpdateEventDto.getComments());
        existingEvent.setAddress(address);
        existingEvent.setNumberOfSeats(numberOfSeats);
        existingEvent.setUpdatedAt(OffsetDateTime.now());

        Event savedEvent = eventRepository.save(existingEvent);

        return eventDtoMapper.toDto(savedEvent);
    }

    @Transactional
    public EventDto deleteEventById(Long id) throws NotFound {
        Event existingEvent = eventRepository.findById(id).orElseThrow(NotFound::new);

        eventRepository.delete(existingEvent);

        return eventDtoMapper.toDto(existingEvent);
    }

    @Transactional
    public EventDto signUpForEvent(Long id, String login) throws NotFound {
        Event existingEvent = eventRepository.findById(id).orElseThrow(NotFound::new);
        Participant existingParticipant = participantRepository.findByLogin(login);
        NumberOfSeats existingNumberOfSeats = existingEvent.getNumberOfSeats();

        List<Participant> participants = existingEvent.getParticipants();
        participants.add(existingParticipant);
        existingEvent.setParticipants(participants);

        existingNumberOfSeats.setFreeSeats(existingNumberOfSeats.getFreeSeats()-1);
        existingNumberOfSeats.setUnconfirmedSeats(existingNumberOfSeats.getUnconfirmedSeats()+1);
        existingEvent.setNumberOfSeats(existingNumberOfSeats);

        Event savedEvent = eventRepository.save(existingEvent);

        return eventDtoMapper.toDto(savedEvent);
    }

    @Transactional
    public EventDto signOutFromEvent(Long id, String login) throws NotFound {
        Event existingEvent = eventRepository.findById(id).orElseThrow(NotFound::new);
        existingEvent.getParticipants().removeIf(participant -> participant.getLogin().equals(login));
        NumberOfSeats existingNumberOfSeats = existingEvent.getNumberOfSeats();

        existingNumberOfSeats.setFreeSeats(existingNumberOfSeats.getFreeSeats()+1);
        existingNumberOfSeats.setUnconfirmedSeats(existingNumberOfSeats.getUnconfirmedSeats()-1);
        existingEvent.setNumberOfSeats(existingNumberOfSeats);

        Event savedEvent = eventRepository.save(existingEvent);
        return eventDtoMapper.toDto(savedEvent);
    }
}
