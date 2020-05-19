package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.BadRequest;
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

    @GetMapping("/organizers-events/{id}")
    public List<EventDto> getEventsByOrganizerId(@PathVariable Long id) throws NotFound {
        return eventService.getEventsByOrganizerId(id);
    }

    @PostMapping()
    public EventDto newEvent(@RequestBody CreateUpdateEventDto createUpdateEventDto) throws BadRequest, NotFound {
        return eventService.createEvent(createUpdateEventDto);
    }

    @PostMapping("{eventId}/{organizerId}")
    public EventDto setOrganizerToEvent(@PathVariable Long eventId, @PathVariable Long organizerId) throws NotFound, BadRequest {
        return eventService.addOrganizerToEvent(eventId, organizerId);
    }

    @PutMapping("/{id}")
    public EventDto updateEventById(@PathVariable Long id, CreateUpdateEventDto createUpdateEventDto) throws NotFound, BadRequest {
        return eventService.updateEventById(id, createUpdateEventDto);
    }

    @DeleteMapping("/{id}")
    public EventDto deleteEventById(@PathVariable Long id) throws NotFound {
        return eventService.deleteEventById(id);
    }
}
