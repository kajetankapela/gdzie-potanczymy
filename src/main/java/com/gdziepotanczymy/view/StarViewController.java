package com.gdziepotanczymy.view;

import com.gdziepotanczymy.controller.exception.AlreadyExists;
import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.StarService;
import com.gdziepotanczymy.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    @GetMapping("/delete-star/{id}")
    public String deleteStar(@PathVariable Long id) throws NotFound {
//        ModelAndView modelAndView = new ModelAndView("all-stars");

        starService.deleteStarById(id);

        return "redirect:/all-stars";
    }

    @GetMapping("/new-star")
    public ModelAndView displayCreateStarForm() {
        CreateUpdateStarDto createUpdateStarDto = new CreateUpdateStarDto();

        ModelAndView modelAndView = new ModelAndView("create_star_form");
        modelAndView.addObject("createUpdateStarDto", createUpdateStarDto);

        return modelAndView;
    }

    @PostMapping("/new-star")
    public String createStar(@ModelAttribute CreateUpdateStarDto createUpdateStarDto) throws AlreadyExists, BadRequest {
        starService.createStar(createUpdateStarDto);

        return "redirect:/all-stars";
    }

    @GetMapping("/update-star/{id}")
    public ModelAndView displayUpdateStarForm(@PathVariable Long id) throws NotFound {
        CreateUpdateStarDto createUpdateStarDto = new CreateUpdateStarDto();
        StarDto existingStar = starService.getStarById(id);

        //todo update tylko w przypadku gdy pole w formularzu jest wypełnione

        ModelAndView modelAndView = new ModelAndView("update_star_form");
        modelAndView.addObject("createUpdateStarDto", createUpdateStarDto);
        modelAndView.addObject("existingStar", existingStar);

        return modelAndView;
    }

    @PostMapping("/update-star/{id}")
    public String updateStar(@PathVariable Long id,
                             @Valid @ModelAttribute CreateUpdateStarDto createUpdateStarDto,
                             BindingResult bindingResult,
                             @ModelAttribute(name = "existingStar") StarDto existingStar) throws BadRequest, AlreadyExists, NotFound {
        if (bindingResult.hasErrors()) {
            return "update_star_form";
        }
        starService.updateStarById(id, createUpdateStarDto);

        return "redirect:/all-stars";
    }
}
