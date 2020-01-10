package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventOrganizerService;
import com.gdziepotanczymy.service.EventService;
import com.gdziepotanczymy.service.OrganizerService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventOrganizerDto;
import com.gdziepotanczymy.service.dto.EventDto;
import com.gdziepotanczymy.service.dto.EventOrganizerDto;
import com.gdziepotanczymy.service.dto.OrganizerDto;
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
public class EventOrganizerViewController {
    private final EventOrganizerService eventOrganizerService;
    private final EventService eventService;
    private final OrganizerService organizerService;

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

    @GetMapping("/new-event-organizer")
    public ModelAndView displayCreatEventOrganizerForm() {
        CreateUpdateEventOrganizerDto createUpdateEventOrganizerDto = new CreateUpdateEventOrganizerDto();
        List<EventDto> events = eventService.getAllEvents();
        List<OrganizerDto> organizers = organizerService.getAllOrganizers();

        ModelAndView modelAndView = new ModelAndView("create_event_organizer_form");
        modelAndView.addObject("createUpdateEventOrganizerDto", createUpdateEventOrganizerDto);
        modelAndView.addObject("events", events);
        modelAndView.addObject("organizers", organizers);

        return modelAndView;
    }

    @PostMapping("/new-event-organizer")
    public String createEventOrganizer(@ModelAttribute CreateUpdateEventOrganizerDto createUpdateEventOrganizerDto) throws NotFound, BadRequest {
        eventOrganizerService.createEventOrganizer(createUpdateEventOrganizerDto);

        return "redirect:/all-event-organizers";
    }
}
