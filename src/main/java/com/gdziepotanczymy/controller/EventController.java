package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventDto;
import com.gdziepotanczymy.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable Long id) throws NotFound {
        return eventService.getEventById(id);
    }

    @PostMapping()
    public EventDto newEvent(@RequestBody CreateUpdateEventDto createUpdateEventDto) {
        return eventService.createEvent(createUpdateEventDto);
    }

    @PutMapping("/{id}")
    public EventDto updateEventById(@PathVariable Long id, CreateUpdateEventDto createUpdateEventDto) throws NotFound {
        return eventService.updateEventById(id, createUpdateEventDto);
    }
}
