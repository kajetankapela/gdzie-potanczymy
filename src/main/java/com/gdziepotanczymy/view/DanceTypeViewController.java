package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.AlreadyExists;
import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.DanceTypeService;
import com.gdziepotanczymy.service.dto.CreateUpdateDanceTypeDto;
import com.gdziepotanczymy.service.dto.DanceTypeDto;
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
public class DanceTypeViewController {
    private final DanceTypeService danceTypeService;

    @GetMapping("/all-dance-types")
    public ModelAndView displaDanceTypesTable() {
        List<DanceTypeDto> danceTypes = danceTypeService.getAllDanceTypes();

        ModelAndView modelAndView = new ModelAndView("dance_types_table");
        modelAndView.addObject("danceTypes", danceTypes);

        return modelAndView;
    }

    @GetMapping("/delete-dance-type/{id}")
    public String deleteDanceType(@PathVariable Long id) throws NotFound {
        ModelAndView modelAndView = new ModelAndView("all-dance-types");

        danceTypeService.deleteDanceTypeById(id);

        return "redirect:/all-dance-types";
    }

    @GetMapping("/new-dance-type")
    public ModelAndView displayCreateDanceTypeForm() {
        CreateUpdateDanceTypeDto createUpdateDanceTypeDto = new CreateUpdateDanceTypeDto();

        ModelAndView modelAndView = new ModelAndView("create_dance_type_form");
        modelAndView.addObject("createUpdateDanceTypeDto", createUpdateDanceTypeDto);

        return modelAndView;
    }

    @PostMapping("/new-dance-type")
    public String createDanceType(@ModelAttribute CreateUpdateDanceTypeDto createUpdateDanceTypeDto) throws AlreadyExists, BadRequest {
        danceTypeService.createDanceType(createUpdateDanceTypeDto);

        return "redirect:/all-dance-types";
    }
}
