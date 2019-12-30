package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.service.DanceTypeService;
import com.gdziepotanczymy.service.dto.DanceTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dance-types")
public class DanceTypeController {
    private final DanceTypeService danceTypeService;

    @GetMapping()
    public List<DanceTypeDto> getAllDanceTypes() {
        return danceTypeService.getAllDanceTypes();
    }
}
