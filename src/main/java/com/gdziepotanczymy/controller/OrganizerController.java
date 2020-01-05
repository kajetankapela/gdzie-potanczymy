package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.OrganizerService;
import com.gdziepotanczymy.service.dto.CreateUpdateOrganizerDto;
import com.gdziepotanczymy.service.dto.OrganizerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/organizers")
public class OrganizerController {
    private final OrganizerService organizerService;

    @GetMapping()
    public List<OrganizerDto> getAllOrganizers() {
        return organizerService.getAllOrganizers();
    }

    @GetMapping("/{id}")
    public OrganizerDto getOrganizerById(@PathVariable Long id) throws NotFound {
        return organizerService.getOrganizerById(id);
    }

    @PostMapping()
    public OrganizerDto newOrganizer(@RequestBody CreateUpdateOrganizerDto createUpdateOrganizerDto) {
        return organizerService.createOrganizer(createUpdateOrganizerDto);
    }

    @PutMapping("/{id}")
    public OrganizerDto updateOrganizerById(@PathVariable Long id, @RequestBody CreateUpdateOrganizerDto createUpdateOrganizerDto) throws NotFound {
        return organizerService.updateOrganizerById(id, createUpdateOrganizerDto);
    }

    @DeleteMapping("/{id}")
    public OrganizerDto deleteOrganizerById(@PathVariable Long id) throws NotFound {
        return organizerService.deleteOrganizerById(id);
    }
}
