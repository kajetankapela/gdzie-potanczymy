package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.AlreadyExists;
import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.StarService;
import com.gdziepotanczymy.service.dto.CreateUpdateStarDto;
import com.gdziepotanczymy.service.dto.StarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public StarDto getStarById(@PathVariable Long id) throws NotFound {
        return starService.getStarById(id);
    }

    @PostMapping()
    public StarDto newStar(@RequestBody CreateUpdateStarDto createUpdateStarDto) throws AlreadyExists, BadRequest {
        return starService.createStar(createUpdateStarDto);
    }

    @PutMapping("/{id}")
    public StarDto updateStarById(@PathVariable Long id, @RequestBody CreateUpdateStarDto createUpdateStarDto) throws NotFound, AlreadyExists, BadRequest {
        return starService.updateStarById(id, createUpdateStarDto);
    }

    @DeleteMapping("/{id}")
    public StarDto deleteStarById(@PathVariable Long id) throws NotFound {
        return starService.deleteStarById(id);
    }
}
