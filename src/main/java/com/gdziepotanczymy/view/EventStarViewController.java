package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventStarService;
import com.gdziepotanczymy.service.dto.EventStarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventStarViewController {
    private final EventStarService eventStarService;

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
}
