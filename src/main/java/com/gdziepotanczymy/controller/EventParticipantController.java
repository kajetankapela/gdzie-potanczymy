package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.service.EventParticipantService;
import com.gdziepotanczymy.service.dto.EventParticipantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event-participants")
public class EventParticipantController {
    private final EventParticipantService eventParticipantService;

    @GetMapping()
    public List<EventParticipantDto> getAllEventParticipants() {
        return eventParticipantService.getAllEventParticipants();
    }
}
