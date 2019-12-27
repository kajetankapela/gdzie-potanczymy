package com.gdziepotanczymy.view;

import com.gdziepotanczymy.service.DanceTypeService;
import com.gdziepotanczymy.service.dto.DanceTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
