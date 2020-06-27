package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.repository.ParticipantRepository;
import com.gdziepotanczymy.service.EventService;
import com.gdziepotanczymy.service.OrganizerService;
import com.gdziepotanczymy.service.ParticipantService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventDto;
import com.gdziepotanczymy.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
    private final ParticipantService participantService;
    private final OrganizerService organizerService;
    private final ParticipantRepository participantRepository;

    @GetMapping("/all-events")
    public ModelAndView displayEventsTable() {
        List<EventDto> events = eventService.getAllEvents();

        ModelAndView modelAndView = new ModelAndView("events_table");
        modelAndView.addObject("events", events);

        return modelAndView;
    }

    @GetMapping("/all-participant-events")
    public ModelAndView displayParticipantEventsTable(Authentication authentication) throws NotFound {
        List<EventDto> myEvents = participantService.getAllParticipantEvents(authentication.getName());
        List<EventDto> moreEvents = participantService.getMoreEvents(authentication.getName());

        ModelAndView modelAndView = new ModelAndView("participant_events_table");
        modelAndView.addObject("myEvents", myEvents);
        modelAndView.addObject("moreEvents", moreEvents);

        return modelAndView;
    }

    @GetMapping("/all-organizer-events")
    public ModelAndView displayOrganizerEventsTable(Authentication authentication) throws NotFound {
        List<EventDto> myEvents = organizerService.getAllOrganizerEvents(authentication.getName());
        List<EventDto> moreEvents = organizerService.getMoreEvents(authentication.getName());

        ModelAndView modelAndView = new ModelAndView("organizer_events_table");
        modelAndView.addObject("myEvents", myEvents);
        modelAndView.addObject("moreEvents", moreEvents);

        return modelAndView;
    }

    @GetMapping("/delete-event/{id}")
    public String deleteEvent(@PathVariable Long id) throws NotFound {

        eventService.deleteEventById(id);

        return "redirect:/";
    }

    @GetMapping("/sign-up/{id}")
    public String signUpForEvent(@PathVariable Long id, Authentication authentication) throws NotFound {
        eventService.signUpForEvent(id, authentication.getName());

        return "redirect:/all-participant-events";
    }

    @GetMapping("/sign-out/{id}")
    public String signOutFromEvent(@PathVariable Long id, Authentication authentication) throws NotFound {
        eventService.signOutFromEvent(id, authentication.getName());

        return "redirect:/all-participant-events";
    }

    @GetMapping("/new-event")
    public ModelAndView displayCreateEventForm() {

        CreateUpdateEventDto createUpdateEventDto = new CreateUpdateEventDto();

        ModelAndView modelAndView = new ModelAndView("create_event_form");
        modelAndView.addObject("createUpdateEventDto", createUpdateEventDto);


        return modelAndView;
    }

    @PostMapping("/new-event")
    public String createEvent(@ModelAttribute CreateUpdateEventDto createUpdateEventDto) throws BadRequest, NotFound {
        eventService.createEvent(createUpdateEventDto);
        return "redirect:/";
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

        return "redirect:/";
    }
}
