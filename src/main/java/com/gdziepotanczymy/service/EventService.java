package com.gdziepotanczymy.service;

import com.gdziepotanczymy.repository.EventRepository;
import com.gdziepotanczymy.service.dto.EventDto;
import com.gdziepotanczymy.service.mapper.EventDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository repository;
    private final EventDtoMapper mapper;

    public List<EventDto> getAllEvents() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
