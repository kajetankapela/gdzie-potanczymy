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
                .name("Organizer_01")
                .role("ORGANIZER")
                .address(Address.builder()
                        .country("Polska")
                        .postalCode("12-345")
                        .city("Wrocław")
                        .street("Główna")
                        .number("12")
                        .build())
                .createdAt(OffsetDateTime.now())
                .email("organizer_01@organizer.com")
                .phoneNumber("123456789")
                .password("organizer01")
                .login("organizer01")
                .build());
        participantRepository.save(Participant.builder()
                .name("Participant_01")
                .role("PARTICIPANT")
                .gender("male")
                .address(Address.builder()
                        .country("Polska")
                        .postalCode("12-345")
                        .city("Wrocław")
                        .street("Nowa")
                        .number("10")
                        .build())
                .phoneNumber("987654321")
                .email("participant_01@participant.com")
                .login("participant01")
                .password("participant01")
                .surname("Participant")
                .createdAt(OffsetDateTime.now())
                .build());
        eventRepository.save(Event.builder()
                .name("Bachaturo")
                .startDate("20/12/2020")
                .endDate("23/12/2020")
                .description("Największy w Posce festiwal tańców pochodzących z Kuby i Dominikany.")
                .comments("brak")
                .organizer(Organizer.builder()
                        .name("Organizer_02")
                        .login("organizer02")
                        .password("organizer02")
                        .phoneNumber("123123123")
                        .email("organizer_02@organizer.com")
                        .createdAt(OffsetDateTime.now())
                        .role("ORGANIZER")
                        .address(Address.builder()
                                .postalCode("10-100")
                                .city("Opole")
                                .street("Piękna")
                                .number("6")
                                .country("Polska")
                                .build())
                        .build())
                .numberOfSeats(NumberOfSeats.builder()
                        .freeSeats(500)
                        .allSeats(500)
                        .unconfirmedSeats(0)
                        .confirmedSeats(0)
                        .build())
                .address(Address.builder()
                        .country("Polska")
                        .number("100A")
                        .street("Długa")
                        .city("Warszawa")
                        .postalCode("90-800")
                        .build())
                .createdAt(OffsetDateTime.now())
                .build());

    }
}
