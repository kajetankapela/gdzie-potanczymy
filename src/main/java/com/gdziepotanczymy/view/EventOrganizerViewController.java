package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventOrganizerService;
import com.gdziepotanczymy.service.dto.EventOrganizerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventOrganizerViewController {
    private final EventOrganizerService eventOrganizerService;

    @GetMapping("/all-event-organizers")
    public ModelAndView displayEventOrganizersTable() {
        List<EventOrganizerDto> eventOrganizers = eventOrganizerService.getAllEventOrganizers();

        ModelAndView modelAndView = new ModelAndView("event_organizers_table");
        modelAndView.addObject("eventOrganizers", eventOrganizers);

        return modelAndView;
    }

    @GetMapping("/delete-event-organizer/{id}")
    public String deleteEventOrganizer(@PathVariable Long id) throws NotFound {
        ModelAndView modelAndView = new ModelAndView("all-event-organizers");

        eventOrganizerService.deleteEventOrganizerById(id);

        return "redirect:/all-event-organizers";
    }
}
