package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.service.ParticipantService;
import com.gdziepotanczymy.service.dto.ParticipantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
