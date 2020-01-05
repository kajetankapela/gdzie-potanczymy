package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventStarService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventStarDto;
import com.gdziepotanczymy.service.dto.EventStarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event-stars")
public class EventStarController {
    private final EventStarService eventStarService;

    @GetMapping()
    public List<EventStarDto> getAllEventStars() {
        return eventStarService.getAllEventStars();
    }

    @GetMapping("/{id}")
    public EventStarDto getEventStarById(@PathVariable Long id) throws NotFound {
        return eventStarService.getEventStarById(id);
    }

    @PostMapping()
    public EventStarDto newEventStar(@RequestBody CreateUpdateEventStarDto createUpdateEventStarDto) throws NotFound {
        return eventStarService.createEventStar(createUpdateEventStarDto);
    }

    @PutMapping("/{id}")
    public EventStarDto updateEventStarById(@PathVariable Long id, @RequestBody CreateUpdateEventStarDto createUpdateEventStarDto) throws NotFound {
        return eventStarService.updateEventStarById(id, createUpdateEventStarDto);
    }

    @DeleteMapping("/{id}")
    public EventStarDto deleteEventStarById(@PathVariable Long id) throws NotFound {
        return eventStarService.deleteEventStarById(id);
    }
}
