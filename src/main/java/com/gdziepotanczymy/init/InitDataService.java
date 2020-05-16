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
    }
}
