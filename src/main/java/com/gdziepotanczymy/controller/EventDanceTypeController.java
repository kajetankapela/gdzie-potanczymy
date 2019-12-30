package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.service.EventDanceTypeService;
import com.gdziepotanczymy.service.dto.EventDanceTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
