package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Address;
import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.model.NumberOfSeats;
import com.gdziepotanczymy.repository.EventRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateEventDto;
import com.gdziepotanczymy.service.dto.EventDto;
//import com.gdziepotanczymy.service.mapper.AddressModelMapper;
import com.gdziepotanczymy.service.mapper.EventDtoMapper;
import com.gdziepotanczymy.service.mapper.NumberOfSeatsModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository repository;
    private final EventDtoMapper eventDtoMapper;
//    private final AddressModelMapper addressModelMapper;
//    private final NumberOfSeatsModelMapper numberOfSeatsModelMapper;

    @Transactional
    public List<EventDto> getAllEvents() {
        return repository.findAll()
                .stream()
                .map(eventDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventDto getEventById(Long id) throws NotFound {
        return repository.findById(id)
                .map(eventDtoMapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public EventDto createEvent(CreateUpdateEventDto createUpdateEventDto) throws BadRequest {
//        isEventOk(createUpdateEventDto);

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

        Event event = Event.builder()
                .name(createUpdateEventDto.getName())
                .startDate(createUpdateEventDto.getStartDate())
                .endDate(createUpdateEventDto.getEndDate())
                .description(createUpdateEventDto.getDescription())
                .comments(createUpdateEventDto.getComments())
                .createdAt(OffsetDateTime.now())
                .address(address)
                .numberOfSeats(numberOfSeats)
                .build();

        Event savedEvent = repository.save(event);

        return eventDtoMapper.toDto(savedEvent);
    }

    @Transactional
    public EventDto updateEventById(Long id, CreateUpdateEventDto createUpdateEventDto) throws NotFound, BadRequest {
//        isEventOk(createUpdateEventDto);

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

        Event existingEvent = repository.findById(id).orElseThrow(NotFound::new);

        existingEvent.setName(createUpdateEventDto.getName());
        existingEvent.setStartDate(createUpdateEventDto.getStartDate());
        existingEvent.setEndDate(createUpdateEventDto.getEndDate());
        existingEvent.setDescription(createUpdateEventDto.getDescription());
        existingEvent.setComments(createUpdateEventDto.getComments());
        existingEvent.setAddress(address);
        existingEvent.setNumberOfSeats(numberOfSeats);
        existingEvent.setUpdatedAt(OffsetDateTime.now());

        Event savedEvent = repository.save(existingEvent);

        return eventDtoMapper.toDto(savedEvent);
    }

    @Transactional
    public EventDto deleteEventById(Long id) throws NotFound {
        Event existingEvent = repository.findById(id).orElseThrow(NotFound::new);

        repository.delete(existingEvent);

        return eventDtoMapper.toDto(existingEvent);
    }

//    private void isEventOk(CreateUpdateEventDto createUpdateEventDto) throws BadRequest {
//        if (createUpdateEventDto.getName() == null || createUpdateEventDto.getName().isEmpty()
//                || createUpdateEventDto.getStartDate() == null || createUpdateEventDto.getEndDate() == null
//                || createUpdateEventDto.getDescription() == null || createUpdateEventDto.getDescription().isEmpty()
//                || createUpdateEventDto.getAddressId() == null
//        ) {
//            throw new BadRequest();
//        }
//    }
}
