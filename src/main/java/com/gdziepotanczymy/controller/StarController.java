package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.service.StarService;
import com.gdziepotanczymy.service.dto.StarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stars")
public class StarController {
    private final StarService starService;

    @GetMapping()
    public List<StarDto> getAllStars() {
        return starService.getAllStars();
    }
}
