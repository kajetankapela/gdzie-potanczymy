package com.gdziepotanczymy.view;

import com.gdziepotanczymy.service.OrganizerService;
import com.gdziepotanczymy.service.dto.OrganizerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrganizerViewController {
    private final OrganizerService organizerService;

    @GetMapping("/all-organizers")
    public ModelAndView displayOrganizersTable() {
        List<OrganizerDto> organizers = organizerService.getAllOrganizers();

        ModelAndView modelAndView = new ModelAndView("organizers_table");
        modelAndView.addObject("organizers", organizers);

        return modelAndView;
    }
}
