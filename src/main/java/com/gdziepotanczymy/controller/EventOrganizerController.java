package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.service.EventOrganizerService;
import com.gdziepotanczymy.service.dto.EventOrganizerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event-organizers")
public class EventOrganizerController {
    private final EventOrganizerService eventOrganizerService;

    @GetMapping()
    public List<EventOrganizerDto> getAllEventOrganizers() {
        return eventOrganizerService.getAllEventOrganizers();
    }
}
