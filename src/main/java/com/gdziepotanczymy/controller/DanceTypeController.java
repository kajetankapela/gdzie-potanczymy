package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.DanceTypeService;
import com.gdziepotanczymy.service.dto.CreateUpdateDanceTypeDto;
import com.gdziepotanczymy.service.dto.DanceTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public DanceTypeDto getDanceTypeById(@PathVariable Long id) throws NotFound {
        return danceTypeService.getDanceTypeById(id);
    }

    @PostMapping()
    public DanceTypeDto newDanceType(@RequestBody CreateUpdateDanceTypeDto createUpdateDanceTypeDto) {
        return danceTypeService.createDanceType(createUpdateDanceTypeDto);
    }

    @PutMapping("/{id}")
    public DanceTypeDto updateDanceTypeById(@PathVariable Long id, @RequestBody CreateUpdateDanceTypeDto createUpdateDanceTypeDto) throws NotFound {
        return danceTypeService.updateDanceTypeById(id, createUpdateDanceTypeDto);
    }

    @DeleteMapping("/{id}")
    public DanceTypeDto deleteDanceTypeById(@PathVariable Long id) throws NotFound {
        return danceTypeService.deleteDanceTypeById(id);
    }
}
