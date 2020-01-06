package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventDanceTypeService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventDanceTypeDto;
import com.gdziepotanczymy.service.dto.EventDanceTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event-dance-types")
public class EventDanceTypeController {
    private final EventDanceTypeService eventDanceTypeService;

    @GetMapping()
    public List<EventDanceTypeDto> getAllEventDanceTypes() {
        return eventDanceTypeService.getAllEventDanceTypes();
    }

    @GetMapping("/{id}")
    public EventDanceTypeDto getEventDanceTypeById(@PathVariable Long id) throws NotFound {
        return eventDanceTypeService.getEventDanceTypeById(id);
    }

    @PostMapping()
    public EventDanceTypeDto newEventDanceType(@RequestBody CreateUpdateEventDanceTypeDto createUpdateEventDanceTypeDto) throws NotFound, BadRequest {
        return eventDanceTypeService.createEventDanceType(createUpdateEventDanceTypeDto);
    }

    @PutMapping("/{id}")
    public EventDanceTypeDto updateEventDanceTypeById(@PathVariable Long id, @RequestBody CreateUpdateEventDanceTypeDto createUpdateEventDanceTypeDto) throws NotFound {
        return eventDanceTypeService.updateEventDanceTypeById(id, createUpdateEventDanceTypeDto);
    }

    @DeleteMapping("/{id}")
    public EventDanceTypeDto deleteEventDanceTypeById(@PathVariable Long id) throws NotFound {
        return eventDanceTypeService.deleteEventDanceTypeById(id);
    }
}
