package com.gdziepotanczymy.init;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Admin;
import com.gdziepotanczymy.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class InitDataService {
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
    }
}
