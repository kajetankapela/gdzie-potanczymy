package com.gdziepotanczymy.init;

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

    @PostConstruct
    public void init() {
        addressRepository.save(new Address(null, "Polska", "49-300", "Brzeg", "Rybacka", "4/4", OffsetDateTime.now(), null));
        danceTypeRepository.save(new DanceType(null, "bachata", OffsetDateTime.now(), null));
        eventRepository.save(new Event(null, "bachaturro", OffsetDateTime.now(), OffsetDateTime.now(), "jakiś fajny festiwal", 1L, OffsetDateTime.now(), null));
        organizerRepository.save(new Organizer(null, "LofToDance", "111222333", 1L, 1L, OffsetDateTime.now(), null));
        participantRepository.save(new Participant(null, "Kajetan", "Kapela", "668165940", 3L, 4L, OffsetDateTime.now(), null));
        starRepository.save(new Star(null, "Daniel & Desire", OffsetDateTime.now(), null));
        userRepository.save(new User(null, "admin", "password", "admin@gdzie-potanczymy.pl", OffsetDateTime.now(), null));

    }
}
