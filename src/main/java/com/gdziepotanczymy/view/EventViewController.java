package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Participant;
import com.gdziepotanczymy.repository.ParticipantRepository;
import com.gdziepotanczymy.service.EventService;
import com.gdziepotanczymy.service.ParticipantService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventDto;
import com.gdziepotanczymy.service.dto.EventDto;
import com.gdziepotanczymy.service.dto.ParticipantDto;
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
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class EventViewController {
    private final EventService eventService;
    private final ParticipantService participantService;
    private final ParticipantRepository participantRepository;

    @GetMapping("/all-events")
    public ModelAndView displayEventsTable() {
        List<EventDto> events = eventService.getAllEvents();

        ModelAndView modelAndView = new ModelAndView("events_table");
        modelAndView.addObject("events", events);

        return modelAndView;
    }

    @GetMapping("/all-participant-events/{login}")
    public ModelAndView displayParticipantEventsTable(@PathVariable String login) throws NotFound {
        List<EventDto> events = eventService.getAllEvents();
        ParticipantDto participant = participantService.getParticipantByLogin(login);

        List<EventDto> myEvents = new ArrayList<>();
        List<EventDto> moreEvents = new ArrayList<>();

        for (EventDto event : events) {
            if (participant.getEvents().contains(event)) {
                myEvents.add(event);
            } else {
                moreEvents.add(event);
            }
        }
        System.out.println(myEvents);
        System.out.println(moreEvents);

        ModelAndView modelAndView = new ModelAndView("participant_events_table");
        modelAndView.addObject("myEvents", myEvents);
        modelAndView.addObject("moreEvents", moreEvents);

        return modelAndView;
    }

    @GetMapping("/delete-event/{id}")
    public String deleteEvent(@PathVariable Long id) throws NotFound {

        eventService.deleteEventById(id);

        return "redirect:/all-events";
    }

    @GetMapping("/sign-up/{id}/{login}")
    public String signUpForEvent(@PathVariable Long id, @PathVariable String login) throws NotFound {
        eventService.signUpForEvent(id, login);


        return "redirect:/all-participant-events/"+login;
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
