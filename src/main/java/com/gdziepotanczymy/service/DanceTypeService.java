package com.gdziepotanczymy.service;

import com.gdziepotanczymy.repository.DanceTypeRepository;
import com.gdziepotanczymy.service.dto.DanceTypeDto;
import com.gdziepotanczymy.service.mapper.DanceTypeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DanceTypeService {
    private final DanceTypeRepository repository;
    private final DanceTypeDtoMapper mapper;

    public List<DanceTypeDto> getAllDanceTypes() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}