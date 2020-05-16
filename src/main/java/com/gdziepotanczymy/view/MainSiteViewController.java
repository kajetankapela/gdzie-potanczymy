package com.gdziepotanczymy.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MainSiteViewController {

    @GetMapping("")
    public ModelAndView displayMainSite() {
        ModelAndView modelAndView = new ModelAndView("main_site");

        return modelAndView;
    }
}
