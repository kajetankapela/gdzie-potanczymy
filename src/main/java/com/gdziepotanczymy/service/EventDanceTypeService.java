package com.gdziepotanczymy.service;

import com.gdziepotanczymy.repository.EventDanceTypeRepository;
import com.gdziepotanczymy.service.dto.EventDanceTypeDto;
import com.gdziepotanczymy.service.mapper.EventDanceTypeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventDanceTypeService {
    private final EventDanceTypeRepository repository;
    private final EventDanceTypeDtoMapper mapper;

    public List<EventDanceTypeDto> getAllEventDanceTypes() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
