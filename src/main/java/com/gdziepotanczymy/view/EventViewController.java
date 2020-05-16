package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventService;
import com.gdziepotanczymy.service.OrganizerService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventDto;
import com.gdziepotanczymy.service.dto.EventDto;
import com.gdziepotanczymy.service.dto.OrganizerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class EventViewController {
    private final EventService eventService;
    private final OrganizerService organizerService;

    @GetMapping("/all-events")
    public ModelAndView displayEventsTable() {
        List<EventDto> events = eventService.getAllEvents();

        ModelAndView modelAndView = new ModelAndView("events_table");
        modelAndView.addObject("events", events);

        return modelAndView;
    }
//
//    @GetMapping("/all-organizer-events/{id}")
//    public ModelAndView displayOrganizerEventsTable(@PathVariable Long id) throws NotFound {
//        List<EventDto> events = eventService.getEventsByOrganizerId(id);
//
//        ModelAndView modelAndView = new ModelAndView("organizer_events_table");
//        modelAndView.addObject("events", events);
//
//        return modelAndView;
//    }

    @GetMapping("/delete-event/{id}")
    public String deleteEvent(@PathVariable Long id) throws NotFound {

        eventService.deleteEventById(id);

        return "redirect:/all-events";
    }

    @GetMapping("/new-event")
    public ModelAndView displayCreateEventForm() {
        CreateUpdateEventDto createUpdateEventDto = new CreateUpdateEventDto();
        List<OrganizerDto> organizers = organizerService.getAllOrganizers();

        ModelAndView modelAndView = new ModelAndView("create_event_form");
        modelAndView.addObject("createUpdateEventDto", createUpdateEventDto);
        modelAndView.addObject("organizers", organizers);

        return modelAndView;
    }

    @PostMapping("/new-event")
    public String createEvent(@ModelAttribute CreateUpdateEventDto createUpdateEventDto) throws BadRequest {
        eventService.createEvent(createUpdateEventDto);

        return "redirect:/all-events";
    }

    @PostMapping("/set-organizer-to-event/{eventId}/{organizerId}")
    public String addOrganizerToEvent(@PathVariable Long eventId, @PathVariable Long organizerId) throws NotFound, BadRequest {
        eventService.addOrganizerToEvent(eventId, organizerId);

        return "redirect:/all-events";
    }

    @GetMapping("/update-event/{id}")
    public ModelAndView displayUpdateEventForm(@PathVariable Long id) throws NotFound {
        CreateUpdateEventDto createUpdateEventDto = new CreateUpdateEventDto();
        EventDto existingEvent = eventService.getEventById(id);

        ModelAndView modelAndView = new ModelAndView("update_event_form");
        modelAndView.addObject("createUpdateEventDto", createUpdateEventDto);
        modelAndView.addObject("existingEvent", existingEvent);

        return modelAndView;
    }

    @PostMapping("/update-event/{id}")
    public String updateEvent(@PathVariable Long id,
                              @Valid @ModelAttribute CreateUpdateEventDto createUpdateEventDto,
                              BindingResult bindingResult,
                              @ModelAttribute(name = "existingEvent") EventDto existingEvent) throws NotFound, BadRequest {
        if (bindingResult.hasErrors()) {
            return "update_event_form";
        }
        eventService.updateEventById(id, createUpdateEventDto);

        return "redirect:/all-events";
    }
}
