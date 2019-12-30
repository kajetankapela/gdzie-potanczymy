package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.service.EventStarService;
import com.gdziepotanczymy.service.dto.EventStarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
