package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.service.OrganizerService;
import com.gdziepotanczymy.service.dto.OrganizerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/organizers")
public class OrganizerController {
    private final OrganizerService organizerService;

    @GetMapping()
    public List<OrganizerDto> getAllOrganizers() {
        return organizerService.getAllOrganizers();
    }
}
