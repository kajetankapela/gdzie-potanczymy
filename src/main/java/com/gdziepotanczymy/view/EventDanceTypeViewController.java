package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.EventDanceTypeService;
import com.gdziepotanczymy.service.dto.EventDanceTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventDanceTypeViewController {
    private final EventDanceTypeService eventDanceTypeService;

    @GetMapping("/all-event-dance-types")
    public ModelAndView displayEventDanceTypesTable() {
        List<EventDanceTypeDto> eventDanceTypes = eventDanceTypeService.getAllEventDanceTypes();

        ModelAndView modelAndView = new ModelAndView("event_dance_types_table");
        modelAndView.addObject("eventDanceTypes", eventDanceTypes);

        return modelAndView;
    }

    @GetMapping("/delete-event-dance-type/{id}")
    public String deleteEventDanceType(@PathVariable Long id) throws NotFound {
        ModelAndView modelAndView = new ModelAndView("all-event-dance-types");

        eventDanceTypeService.deleteEventDanceTypeById(id);

        return "redirect:/all-event-dance-types";
    }
}
