package com.gdziepotanczymy.view;

import com.gdziepotanczymy.service.StarService;
import com.gdziepotanczymy.service.dto.StarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StarViewController {
    private final StarService starService;

    @GetMapping("/all-stars")
    public ModelAndView displayStars() {
        List<StarDto> stars = starService.getAllStars();

        ModelAndView modelAndView = new ModelAndView("stars_table");
        modelAndView.addObject("stars", stars);

        return modelAndView;
    }
}
