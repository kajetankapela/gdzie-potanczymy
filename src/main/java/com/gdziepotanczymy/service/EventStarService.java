package com.gdziepotanczymy.service;

import com.gdziepotanczymy.repository.EventStarRepository;
import com.gdziepotanczymy.service.dto.EventStarDto;
import com.gdziepotanczymy.service.mapper.EventStarDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventStarService {
    private final EventStarRepository repository;
    private final EventStarDtoMapper mapper;

    @Transactional
    public List<EventStarDto> getAllEventStars() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
