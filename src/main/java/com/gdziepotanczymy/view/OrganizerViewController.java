package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.AlreadyExists;
import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.OrganizerService;
import com.gdziepotanczymy.service.dto.CreateUpdateOrganizerDto;
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
public class OrganizerViewController {
    private final OrganizerService organizerService;

    @GetMapping("/all-organizers")
    public ModelAndView displayOrganizersTable() {
        List<OrganizerDto> organizers = organizerService.getAllOrganizers();

        ModelAndView modelAndView = new ModelAndView("organizers_table");
        modelAndView.addObject("organizers", organizers);

        return modelAndView;
    }

    @GetMapping("/delete-organizer/{id}")
    public String deleteOrganizer(@PathVariable Long id) throws NotFound {
        ModelAndView modelAndView = new ModelAndView("all-organizers");

        organizerService.deleteOrganizerById(id);

        return "redirect:/all-organizers";
    }

    @GetMapping("/new-organizer")
    public ModelAndView displayCreateOrganizerForm() {
        CreateUpdateOrganizerDto createUpdateOrganizerDto = new CreateUpdateOrganizerDto();

        ModelAndView modelAndView = new ModelAndView("create_organizer_form");
        modelAndView.addObject("createUpdateOrganizerDto", createUpdateOrganizerDto);

        return modelAndView;
    }

    @PostMapping("/new-organizer")
    public String createOrganizer(@ModelAttribute CreateUpdateOrganizerDto createUpdateOrganizerDto) throws AlreadyExists, BadRequest {
        organizerService.createOrganizer(createUpdateOrganizerDto);

        return "redirect:/all-organizers";
    }
}
