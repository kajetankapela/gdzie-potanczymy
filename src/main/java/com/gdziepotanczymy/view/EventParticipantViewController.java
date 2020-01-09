package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventParticipantService;
import com.gdziepotanczymy.service.dto.EventParticipantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventParticipantViewController {
    private final EventParticipantService eventParticipantService;

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
}
