package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Address;
import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.model.NumberOfSeats;
import com.gdziepotanczymy.model.Organizer;
import com.gdziepotanczymy.repository.EventRepository;
import com.gdziepotanczymy.repository.OrganizerRepository;
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
    private final EventRepository eventRepository;
    private final EventDtoMapper eventDtoMapper;
    private final OrganizerRepository organizerRepository;

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
    public List<EventDto> getEventsByOrganizerId(Long id) throws NotFound {
        return eventRepository.findByOrganizerId(id)
                .stream()
                .map(eventDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventDto createEvent(CreateUpdateEventDto createUpdateEventDto) throws BadRequest {

        Address address = Address.builder()
                .country(createUpdateEventDto.getCreateUpdateAddressDto().getCountry())
                .postalCode(createUpdateEventDto.getCreateUpdateAddressDto().getPostalCode())
                .city(createUpdateEventDto.getCreateUpdateAddressDto().getCity())
                .street(createUpdateEventDto.getCreateUpdateAddressDto().getStreet())
                .number(createUpdateEventDto.getCreateUpdateAddressDto().getNumber())
                .build();

        NumberOfSeats numberOfSeats = NumberOfSeats.builder()
                .allSeats(createUpdateEventDto.getCreateUpdateNumberOfSeatsDto().getAllSeats())
                .freeSeats(createUpdateEventDto.getCreateUpdateNumberOfSeatsDto().getFreeSeats())
                .unconfirmedSeats(createUpdateEventDto.getCreateUpdateNumberOfSeatsDto().getUnconfirmedSeats())
                .confirmedSeats(createUpdateEventDto.getCreateUpdateNumberOfSeatsDto().getConfirmedSeats())
                .build();
        Organizer organizer = Organizer.builder()
                .name("name")
                .login("login")
                .password("password")
                .email("email@email")
                .phoneNumber("phoneNumber")
                .address(Address.builder()
                        .country("country")
                        .postalCode("postalCode")
                        .city("city")
                        .street("street")
                        .number("number")
                        .build())
                .createdAt(OffsetDateTime.now())
                .build();

        Event event = Event.builder()
                .name(createUpdateEventDto.getName())
                .startDate(createUpdateEventDto.getStartDate())
                .endDate(createUpdateEventDto.getEndDate())
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
    public EventDto addOrganizerToEvent(Long eventId, Long organizerId) throws BadRequest, NotFound {
        Event event = eventRepository.findById(eventId).orElseThrow(NotFound::new);

        Organizer organizer = organizerRepository.findById(organizerId).orElseThrow(NotFound::new);

        event.setOrganizer(organizer);

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
                .freeSeats(createUpdateEventDto.getCreateUpdateNumberOfSeatsDto().getFreeSeats())
                .unconfirmedSeats(createUpdateEventDto.getCreateUpdateNumberOfSeatsDto().getUnconfirmedSeats())
                .confirmedSeats(createUpdateEventDto.getCreateUpdateNumberOfSeatsDto().getConfirmedSeats())
                .build();

        Event existingEvent = eventRepository.findById(id).orElseThrow(NotFound::new);

        existingEvent.setName(createUpdateEventDto.getName());
        existingEvent.setStartDate(createUpdateEventDto.getStartDate());
        existingEvent.setEndDate(createUpdateEventDto.getEndDate());
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
}
