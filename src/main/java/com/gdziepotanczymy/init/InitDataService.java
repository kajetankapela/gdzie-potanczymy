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
    private final AdminRepository adminRepository;

    @PostConstruct
    public void init() throws NotFound {
        adminRepository.save(Admin.builder()
                .name("Kajetan")
                .login("admin_kajetan")
                .password("kajetan_password")
                .email("kajetan.kapela@gmail.com")
                .phoneNumber("668165940")
                .role("ADMIN")
                .createdAt(OffsetDateTime.now())
                .build());
        adminRepository.save(Admin.builder()
                .name("Patrycja")
                .login("admin_patrycja")
                .password("patrycja_password")
                .email("pe.kapela92@gmail.com")
                .phoneNumber("730108317")
                .role("ADMIN")
                .createdAt(OffsetDateTime.now())
                .build());
        organizerRepository.save(Organizer.builder()
                .name("organizer01")
                .role("ORGANIZER")
                .address(Address.builder()
                        .country("pol")
                        .postalCode("123")
                        .city("cit")
                        .street("asd")
                        .number("12")
                        .build())
                .createdAt(OffsetDateTime.now())
                .email("org@org")
                .phoneNumber("123123")
                .password("org")
                .login("org")
                .build());
        participantRepository.save(Participant.builder()
                .name("participant")
                .role("PARTICIPANT")
                .gender("male")
                .address(Address.builder()
                        .country("pol")
                        .postalCode("asd")
                        .city("asd")
                        .street("asd")
                        .number("asd")
                        .build())
                .phoneNumber("123123")
                .email("part@part")
                .login("part")
                .password("part")
                .surname("dasd")
                .createdAt(OffsetDateTime.now())
                .build());
        eventRepository.save(Event.builder()
                .name("bachaturo")
                .startDate("20/12/2020")
                .endDate("21/12/2020")
                .description("desc")
                .comments("comm")
                .organizer(Organizer.builder()
                        .name("org")
                        .login("org2")
                        .password("org2")
                        .phoneNumber("123")
                        .email("asd@ads")
                        .createdAt(OffsetDateTime.now())
                        .role("ORGANIZER")
                        .address(Address.builder()
                                .postalCode("add")
                                .city("ads")
                                .street("asd")
                                .number("ads")
                                .country("asd")
                                .build())
                        .build())
                .numberOfSeats(NumberOfSeats.builder()
                        .freeSeats(130)
                        .allSeats(130)
                        .unconfirmedSeats(0)
                        .confirmedSeats(0)
                        .build())
                .address(Address.builder()
                        .country("ads")
                        .number("12")
                        .street("asd")
                        .city("asd")
                        .postalCode("asd")
                        .build())
                .createdAt(OffsetDateTime.now())
                .build());

    }
}
