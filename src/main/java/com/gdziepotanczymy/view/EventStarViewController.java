package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventService;
import com.gdziepotanczymy.service.EventStarService;
import com.gdziepotanczymy.service.StarService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventStarDto;
import com.gdziepotanczymy.service.dto.EventDto;
import com.gdziepotanczymy.service.dto.EventStarDto;
import com.gdziepotanczymy.service.dto.StarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventStarViewController {
    private final EventStarService eventStarService;
    private final EventService eventService;
    private final StarService starService;

    @GetMapping("/all-event-stars")
    public ModelAndView displayEventStarsTable() {
        List<EventStarDto> eventStars = eventStarService.getAllEventStars();

        ModelAndView modelAndView = new ModelAndView("event_stars_table");
        modelAndView.addObject("eventStars", eventStars);

        return modelAndView;
    }

    @GetMapping("/delete-event-star/{id}")
    public String deleteEventStar(@PathVariable Long id) throws NotFound {
        ModelAndView modelAndView = new ModelAndView("all-event-stars");

        eventStarService.deleteEventStarById(id);

        return "redirect:/all-event-stars";
    }

    @GetMapping("/new-event-star")
    public ModelAndView displayCreateEventStarForm() {
        CreateUpdateEventStarDto createUpdateEventStarDto = new CreateUpdateEventStarDto();
        List<EventDto> events = eventService.getAllEvents();
        List<StarDto> stars = starService.getAllStars();

        ModelAndView modelAndView = new ModelAndView("create_event_star_form");
        modelAndView.addObject("createUpdateEventStarDto", createUpdateEventStarDto);
        modelAndView.addObject("events", events);
        modelAndView.addObject("stars", stars);

        return modelAndView;
    }

    @PostMapping("/new-event-star")
    public String createEventStar(@ModelAttribute CreateUpdateEventStarDto createUpdateEventStarDto) throws NotFound, BadRequest {
        eventStarService.createEventStar(createUpdateEventStarDto);

        return "redirect:/all-event-stars";
    }
}
