package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.ParticipantService;
import com.gdziepotanczymy.service.dto.ParticipantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ParticipantViewController {
    private final ParticipantService participantService;

    @GetMapping("/all-participants")
    public ModelAndView displayParticipantsTable() {
        List<ParticipantDto> participants = participantService.getAllParticipants();

        ModelAndView modelAndView = new ModelAndView("participants_table");
        modelAndView.addObject("participants", participants);

        return modelAndView;
    }

    @GetMapping("/delete-participant/{id}")
    public String deleteParticipant(@PathVariable Long id) throws NotFound {
        ModelAndView modelAndView = new ModelAndView("all-participant");

        participantService.deleteParticipantById(id);

        return "redirect:/all-participants";
    }
}
