package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventParticipantService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventParticipantDto;
import com.gdziepotanczymy.service.dto.EventParticipantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public EventParticipantDto getEventParticipantById(@PathVariable Long id) throws NotFound {
        return eventParticipantService.getEventParticipantById(id);
    }

    @PostMapping()
    public EventParticipantDto newEventParticipant(@RequestBody CreateUpdateEventParticipantDto createUpdateEventParticipantDto) throws NotFound {
        return eventParticipantService.createEventParticipant(createUpdateEventParticipantDto);
    }

    @PutMapping("/{id}")
    public EventParticipantDto updateEventParticipantById(@PathVariable Long id, @RequestBody CreateUpdateEventParticipantDto createUpdateEventParticipantDto) throws NotFound {
        return eventParticipantService.updateEventParticipantById(id, createUpdateEventParticipantDto);
    }

    @DeleteMapping("/{id}")
    public EventParticipantDto deleteEventParticipantById(@PathVariable Long id) throws NotFound {
        return eventParticipantService.deleteEventParticipantById(id);
    }
}
