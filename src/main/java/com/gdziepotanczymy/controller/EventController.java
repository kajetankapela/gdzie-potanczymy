package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.service.EventService;
import com.gdziepotanczymy.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @GetMapping()
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }
}
