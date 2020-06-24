package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.AlreadyExists;
import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Address;
import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.model.Organizer;
import com.gdziepotanczymy.model.Participant;
import com.gdziepotanczymy.repository.EventRepository;
import com.gdziepotanczymy.repository.OrganizerRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateOrganizerDto;
import com.gdziepotanczymy.service.dto.EventDto;
import com.gdziepotanczymy.service.dto.OrganizerDto;
import com.gdziepotanczymy.service.dto.ParticipantDto;
import com.gdziepotanczymy.service.mapper.EventDtoMapper;
import com.gdziepotanczymy.service.mapper.OrganizerDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrganizerService {
    private final OrganizerRepository repository;
    private final OrganizerDtoMapper organizerDtoMapper;
    private final EventDtoMapper eventDtoMapper;
    private final EventRepository eventRepository;

    @Transactional
    public List<OrganizerDto> getAllOrganizers() {
        return repository.findAll()
                .stream()
                .map(organizerDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrganizerDto getOrganizerById(Long id) throws NotFound {
        return repository.findById(id)
                .map(organizerDtoMapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public List<EventDto> getAllOrganizerEvents(String login) {
        Organizer organizer = repository.findByLogin(login);
        return organizer.getEvents().stream()
                .map(eventDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Map<String, List<Participant>> getAllParticipantsOfOrganizerEvents(String login) {
        Organizer organizer = repository.findByLogin(login);
        List<Event> events = eventRepository.findAllByOrganizer(organizer);
        Map<String, List<Participant>> participantOfOrganizerEvents = new HashMap<>();
        for (Event event : events) {
            participantOfOrganizerEvents.put(event.getName(), event.getParticipants());
        }
        return participantOfOrganizerEvents;
    }

    @Transactional
    public List<EventDto> getMoreEvents(String login) {
        Organizer organizer = repository.findByLogin(login);
        return eventRepository.findAllByOrganizerIsNot(organizer)
                .stream()
                .map(eventDtoMapper::toDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public OrganizerDto createOrganizer(CreateUpdateOrganizerDto createUpdateOrganizerDto) throws BadRequest, AlreadyExists {

        Address address = Address.builder()
                .country(createUpdateOrganizerDto.getCreateUpdateAddressDto().getCountry())
                .postalCode(createUpdateOrganizerDto.getCreateUpdateAddressDto().getPostalCode())
                .city(createUpdateOrganizerDto.getCreateUpdateAddressDto().getCity())
                .street(createUpdateOrganizerDto.getCreateUpdateAddressDto().getStreet())
                .number(createUpdateOrganizerDto.getCreateUpdateAddressDto().getNumber())
                .build();

        Organizer organizer = Organizer.builder()
                .name(createUpdateOrganizerDto.getName())
                .login(createUpdateOrganizerDto.getLogin())
                .password(createUpdateOrganizerDto.getPassword())
                .email(createUpdateOrganizerDto.getEmail())
                .phoneNumber(createUpdateOrganizerDto.getPhoneNumber())
                .role("ORGANIZER")
                .address(address)
                .createdAt(OffsetDateTime.now())
                .build();

        Organizer savedOrganizer = repository.save(organizer);

        return organizerDtoMapper.toDto(savedOrganizer);
    }

    @Transactional
    public OrganizerDto updateOrganizerById(Long id, CreateUpdateOrganizerDto createUpdateOrganizerDto) throws NotFound, BadRequest, AlreadyExists {

        Organizer existingOrganizer = repository.findById(id).orElseThrow(NotFound::new);

        Address address = Address.builder()
                .country(createUpdateOrganizerDto.getCreateUpdateAddressDto().getCountry())
                .postalCode(createUpdateOrganizerDto.getCreateUpdateAddressDto().getPostalCode())
                .city(createUpdateOrganizerDto.getCreateUpdateAddressDto().getCity())
                .street(createUpdateOrganizerDto.getCreateUpdateAddressDto().getStreet())
                .number(createUpdateOrganizerDto.getCreateUpdateAddressDto().getNumber())
                .build();

        existingOrganizer.setName(createUpdateOrganizerDto.getName());
        existingOrganizer.setLogin(createUpdateOrganizerDto.getLogin());
        existingOrganizer.setPassword(createUpdateOrganizerDto.getPassword());
        existingOrganizer.setEmail(createUpdateOrganizerDto.getEmail());
        existingOrganizer.setPhoneNumber(createUpdateOrganizerDto.getPhoneNumber());
        existingOrganizer.setAddress(address);
        existingOrganizer.setUpdatedAt(OffsetDateTime.now());

        Organizer savedOrganizer = repository.save(existingOrganizer);

        return organizerDtoMapper.toDto(savedOrganizer);
    }

    @Transactional
    public OrganizerDto deleteOrganizerById(Long id) throws NotFound {
        Organizer existingOrganizer = repository.findById(id).orElseThrow(NotFound::new);

        repository.delete(existingOrganizer);

        return organizerDtoMapper.toDto(existingOrganizer);
    }
}
