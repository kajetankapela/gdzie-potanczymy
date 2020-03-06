package com.gdziepotanczymy.init;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.*;
import com.gdziepotanczymy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class InitDataService {
    private final DanceTypeRepository danceTypeRepository;
    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final ParticipantRepository participantRepository;
    private final StarRepository starRepository;

    @PostConstruct
    public void init() throws NotFound {
        danceTypeRepository.save(DanceType.builder()
                .name("dance type")
                .description("description")
                .comments("comments")
                .createdAt(OffsetDateTime.now())
                .build());
        starRepository.save(Star.builder()
                .name("star")
                .country("country")
                .description("description")
                .comments("comments")
                .createdAt(OffsetDateTime.now())
                .build());
        organizerRepository.save(Organizer.builder()
                .name("Raś")
                .login("ras")
                .password("ras")
                .email("ras@ras")
                .phoneNumber("123123")
                .createdAt(OffsetDateTime.now())
                .address(Address.builder()
                        .country("Polska")
                        .postalCode("12-345")
                        .city("Kraków")
                        .street("Rasiowa")
                        .number("100")
                        .build())
                .build());
        participantRepository.save(Participant.builder()
                .name("Kajetan")
                .surname("Kapela")
                .gender("male")
                .login("kajok")
                .password("kajko")
                .email("kajko@kajko")
                .phoneNumber("123123")
                .createdAt(OffsetDateTime.now())
                .address(Address.builder()
                        .country("polska")
                        .postalCode("49-300")
                        .city("Brzeg")
                        .street("Rybacka")
                        .number("4/4")
                        .build())
                .build());
        eventRepository.save(Event.builder()
                .name("Bachaturo")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .description("description")
                .comments("comments")
                .createdAt(OffsetDateTime.now())
                .address(Address.builder()
                        .country("country")
                        .postalCode("00-000")
                        .city("city")
                        .street("street")
                        .number("00")
                        .build())
                .numberOfSeats(NumberOfSeats.builder()
                        .allSeats(100)
                        .freeSeats(0)
                        .confirmedSeats(50)
                        .unconfirmedSeats(50)
                        .build())
                .build());
    }
}
