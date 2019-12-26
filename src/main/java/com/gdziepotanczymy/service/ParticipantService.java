package com.gdziepotanczymy.service;

import com.gdziepotanczymy.repository.ParticipantRepository;
import com.gdziepotanczymy.service.dto.ParticipantDto;
import com.gdziepotanczymy.service.mapper.ParticipantDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository repository;
    private final ParticipantDtoMapper mapper;

    public List<ParticipantDto> getAllParticipants() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
