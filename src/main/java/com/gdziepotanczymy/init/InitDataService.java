package com.gdziepotanczymy.init;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.*;
import com.gdziepotanczymy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class InitDataService {
    private final AddressRepository addressRepository;
    private final DanceTypeRepository danceTypeRepository;
    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final ParticipantRepository participantRepository;
    private final StarRepository starRepository;
    private final UserRepository userRepository;
    private final EventDanceTypeRepository eventDanceTypeRepository;
    private final EventOrganizerRepository eventOrganizerRepository;
    private final EventParticipantRepository eventParticipantRepository;
    private final EventStarRepository eventStarRepository;

    @PostConstruct
    public void init() throws NotFound {
        addressRepository.save(new Address(null, "Polska", "49-300", "Brzeg", "Rybacka", "4/4", OffsetDateTime.now(), null));
        danceTypeRepository.save(new DanceType(null, "bachata", OffsetDateTime.now(), null));
        eventRepository.save(new Event(null, "bachaturro", OffsetDateTime.now(), OffsetDateTime.now(), "jakiś fajny festiwal", 1L, OffsetDateTime.now(), null));
        organizerRepository.save(new Organizer(null, "LofToDance", "111222333", 1L, 1L, OffsetDateTime.now(), null));
        participantRepository.save(new Participant(null, "Kajetan", "Kapela", "668165940", 3L, 4L, OffsetDateTime.now(), null));
        starRepository.save(new Star(null, "Daniel & Desire", OffsetDateTime.now(), null));
        userRepository.save(new User(null, "admin", "password", "admin@gdzie-potanczymy.pl", OffsetDateTime.now(), null));
        eventDanceTypeRepository.save(new EventDanceType(null, OffsetDateTime.now(), null, eventRepository.findById(1L).orElseThrow(NotFound::new), danceTypeRepository.findById(1L).orElseThrow(NotFound::new)));
        eventOrganizerRepository.save(new EventOrganizer(null, OffsetDateTime.now(), null, eventRepository.findById(1L).orElseThrow(NotFound::new), organizerRepository.findById(1L).orElseThrow(NotFound::new)));
        eventParticipantRepository.save(new EventParticipant(null, OffsetDateTime.now(), null, eventRepository.findById(1L).orElseThrow(NotFound::new), participantRepository.findById(1L).orElseThrow(NotFound::new)));
        eventStarRepository.save(new EventStar(null, OffsetDateTime.now(), null, eventRepository.findById(1L).orElseThrow(NotFound::new), starRepository.findById(1L).orElseThrow(NotFound::new)));

        addressRepository.save(new Address(null, "Polska", "49-300", "Brzeg", "Rybacka", "4/4", OffsetDateTime.now(), null));
        danceTypeRepository.save(new DanceType(null, "bachata", OffsetDateTime.now(), null));
        eventRepository.save(new Event(null, "bachaturro", OffsetDateTime.now(), OffsetDateTime.now(), "jakiś fajny festiwal", 1L, OffsetDateTime.now(), null));
        organizerRepository.save(new Organizer(null, "LofToDance", "111222333", 1L, 1L, OffsetDateTime.now(), null));
        participantRepository.save(new Participant(null, "Kajetan", "Kapela", "668165940", 3L, 4L, OffsetDateTime.now(), null));
        starRepository.save(new Star(null, "Daniel & Desire", OffsetDateTime.now(), null));
        userRepository.save(new User(null, "admin", "password", "admin@gdzie-potanczymy.pl", OffsetDateTime.now(), null));
        eventDanceTypeRepository.save(new EventDanceType(null, OffsetDateTime.now(), null, eventRepository.findById(2L).orElseThrow(NotFound::new), danceTypeRepository.findById(2L).orElseThrow(NotFound::new)));
        eventOrganizerRepository.save(new EventOrganizer(null, OffsetDateTime.now(), null, eventRepository.findById(2L).orElseThrow(NotFound::new), organizerRepository.findById(2L).orElseThrow(NotFound::new)));
        eventParticipantRepository.save(new EventParticipant(null, OffsetDateTime.now(), null, eventRepository.findById(2L).orElseThrow(NotFound::new), participantRepository.findById(2L).orElseThrow(NotFound::new)));
        eventStarRepository.save(new EventStar(null, OffsetDateTime.now(), null, eventRepository.findById(2L).orElseThrow(NotFound::new), starRepository.findById(2L).orElseThrow(NotFound::new)));
    }
}
