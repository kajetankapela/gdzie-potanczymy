package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.ParticipantService;
import com.gdziepotanczymy.service.dto.CreateUpdateParticipantDto;
import com.gdziepotanczymy.service.dto.ParticipantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    @GetMapping()
    public List<ParticipantDto> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/{id}")
    public ParticipantDto getParticipantById(@PathVariable Long id) throws NotFound {
        return participantService.getParticipantById(id);
    }

    @PostMapping()
    public ParticipantDto newParticipant(@RequestBody CreateUpdateParticipantDto createUpdateParticipantDto) {
        return participantService.createParticipant(createUpdateParticipantDto);
    }

    @PutMapping("/{id}")
    public ParticipantDto updateParticipantById(@PathVariable Long id, @RequestBody CreateUpdateParticipantDto createUpdateParticipantDto) throws NotFound {
        return participantService.updateParticipantById(id, createUpdateParticipantDto);
    }

    @DeleteMapping("{id}")
    public ParticipantDto deleteParticipantById(@PathVariable Long id) throws NotFound {
        return participantService.deleteParticipantById(id);
    }
}
