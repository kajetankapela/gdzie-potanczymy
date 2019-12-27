package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.repository.StarRepository;
import com.gdziepotanczymy.service.dto.StarDto;
import com.gdziepotanczymy.service.mapper.StarDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StarService {
    private final StarRepository repository;
    private final StarDtoMapper mapper;

    @Transactional
    public List<StarDto> getAllStars() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public StarDto getStarById(Long id) throws NotFound {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFound::new);
    }
}
