package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventParticipantService;
import com.gdziepotanczymy.service.EventService;
import com.gdziepotanczymy.service.ParticipantService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventParticipantDto;
import com.gdziepotanczymy.service.dto.EventDto;
import com.gdziepotanczymy.service.dto.EventParticipantDto;
import com.gdziepotanczymy.service.dto.ParticipantDto;
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
public class EventParticipantViewController {
    private final EventParticipantService eventParticipantService;
    private final EventService eventService;
    private final ParticipantService participantService;

    @GetMapping("/all-event-participants")
    public ModelAndView displayEventParticipantsTable() {
        List<EventParticipantDto> eventParticipants = eventParticipantService.getAllEventParticipants();

        ModelAndView modelAndView = new ModelAndView("event_participants_table");
        modelAndView.addObject("eventParticipants", eventParticipants);

        return modelAndView;
    }

    @GetMapping("/delete-event-participant/{id}")
    public String deleteEventParticipant(@PathVariable Long id) throws NotFound {
        ModelAndView modelAndView = new ModelAndView("all-event-participants");

        eventParticipantService.deleteEventParticipantById(id);

        return "redirect:/all-event-participants";
    }

    @GetMapping("/new-event-participant")
    public ModelAndView displayCreateEventParticipantForm() {
        CreateUpdateEventParticipantDto createUpdateEventParticipantDto = new CreateUpdateEventParticipantDto();
        List<EventDto> events = eventService.getAllEvents();
        List<ParticipantDto> participants = participantService.getAllParticipants();

        ModelAndView modelAndView = new ModelAndView("create_event_participant_form");
        modelAndView.addObject("createUpdateEventParticipantDto", createUpdateEventParticipantDto);
        modelAndView.addObject("events", events);
        modelAndView.addObject("participants", participants);

        return modelAndView;
    }

    @PostMapping("/new-event-participant")
    public String createEventParticipant(@ModelAttribute CreateUpdateEventParticipantDto createUpdateEventParticipantDto) throws NotFound, BadRequest {
        eventParticipantService.createEventParticipant(createUpdateEventParticipantDto);

        return "redirect:/all-event-participants";
    }
}
