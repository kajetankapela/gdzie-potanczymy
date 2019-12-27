package com.gdziepotanczymy.service;

import com.gdziepotanczymy.repository.EventOrganizerRepository;
import com.gdziepotanczymy.service.dto.EventOrganizerDto;
import com.gdziepotanczymy.service.mapper.EventOrganizerDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventOrganizerService {
    private final EventOrganizerRepository repository;
    private final EventOrganizerDtoMapper mapper;

    @Transactional
    public List<EventOrganizerDto> getAllEventOrganizers() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
