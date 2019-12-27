package com.gdziepotanczymy.service;

import com.gdziepotanczymy.repository.EventParticipantRepository;
import com.gdziepotanczymy.service.dto.EventParticipantDto;
import com.gdziepotanczymy.service.mapper.EventParticipantDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventParticipantService {
    private final EventParticipantRepository repository;
    private final EventParticipantDtoMapper mapper;

    @Transactional
    public List<EventParticipantDto> getAllEventParticipants() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
