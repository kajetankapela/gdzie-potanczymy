package com.gdziepotanczymy.view;

import com.gdziepotanczymy.service.EventService;
import com.gdziepotanczymy.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventViewController {
    private final EventService eventService;

    @GetMapping("/all-events")
    public ModelAndView displayEventsTable() {
        List<EventDto> events = eventService.getAllEvents();

        ModelAndView modelAndView = new ModelAndView("events_table");
        modelAndView.addObject("events", events);

        return modelAndView;
    }
}
