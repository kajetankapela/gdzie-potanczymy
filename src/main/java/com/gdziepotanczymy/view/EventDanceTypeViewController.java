package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.DanceTypeService;
import com.gdziepotanczymy.service.EventDanceTypeService;
import com.gdziepotanczymy.service.EventService;
import com.gdziepotanczymy.service.dto.CreateUpdateEventDanceTypeDto;
import com.gdziepotanczymy.service.dto.DanceTypeDto;
import com.gdziepotanczymy.service.dto.EventDanceTypeDto;
import com.gdziepotanczymy.service.dto.EventDto;
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
public class EventDanceTypeViewController {
    private final EventDanceTypeService eventDanceTypeService;
    private final EventService eventService;
    private final DanceTypeService danceTypeService;

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

    @GetMapping("/new-event-dance-type")
    public ModelAndView displayCreateEventDanceTypeForm() {
        CreateUpdateEventDanceTypeDto createUpdateEventDanceTypeDto = new CreateUpdateEventDanceTypeDto();
        List<EventDto> events = eventService.getAllEvents();
        List<DanceTypeDto> danceTypes = danceTypeService.getAllDanceTypes();

        ModelAndView modelAndView = new ModelAndView("create_event_dance_type_form");
        modelAndView.addObject("createUpdateEventDanceTypeDto", createUpdateEventDanceTypeDto);
        modelAndView.addObject("events", events);
        modelAndView.addObject("danceTypes", danceTypes);

        return modelAndView;
    }

    @PostMapping("/new-event-dance-type")
    public String createEventDanceType(@ModelAttribute CreateUpdateEventDanceTypeDto createUpdateEventDanceTypeDto) throws NotFound, BadRequest {
        eventDanceTypeService.createEventDanceType(createUpdateEventDanceTypeDto);

        return "redirect:/all-event-dance-types";
    }
}
