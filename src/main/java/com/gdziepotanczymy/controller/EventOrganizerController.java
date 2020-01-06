package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventOrganizerService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventOrganizerDto;
import com.gdziepotanczymy.service.dto.EventOrganizerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public EventOrganizerDto getEventOrganizerById(@PathVariable Long id) throws NotFound {
        return eventOrganizerService.getEventOrganizerById(id);
    }

    @PostMapping()
    public EventOrganizerDto newEventOrganizer(@RequestBody CreateUpdateEventOrganizerDto createUpdateEventOrganizerDto) throws NotFound, BadRequest {
        return eventOrganizerService.createEventOrganizer(createUpdateEventOrganizerDto);
    }

    @PutMapping("/{id}")
    public EventOrganizerDto updateEventOrganizerById(@PathVariable Long id, @RequestBody CreateUpdateEventOrganizerDto createUpdateEventOrganizerDto) throws NotFound {
        return eventOrganizerService.updateEventOrganizerById(id, createUpdateEventOrganizerDto);
    }

    @DeleteMapping("/{id}")
    public EventOrganizerDto deleteEventOrganizerById(@PathVariable Long id) throws NotFound {
        return eventOrganizerService.deleteEventOrganizerById(id);
    }
}
