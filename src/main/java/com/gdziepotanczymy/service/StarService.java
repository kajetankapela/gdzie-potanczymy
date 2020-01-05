package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Star;
import com.gdziepotanczymy.repository.StarRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateStarDto;
import com.gdziepotanczymy.service.dto.StarDto;
import com.gdziepotanczymy.service.mapper.StarDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
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

    @Transactional
    public StarDto createStar(CreateUpdateStarDto createUpdateStarDto) {
        Star star = Star.builder()
                .name(createUpdateStarDto.getName())
                .createdAt(OffsetDateTime.now())
                .build();

        Star savedStar = repository.save(star);

        return mapper.toDto(savedStar);
    }

    @Transactional
    public StarDto updateStarById(Long id, CreateUpdateStarDto createUpdateStarDto) throws NotFound {
        Star existingStar = repository.findById(id).orElseThrow(NotFound::new);

        existingStar.setName(createUpdateStarDto.getName());
        existingStar.setUpdatedAt(OffsetDateTime.now());

        Star savedStar = repository.save(existingStar);

        return mapper.toDto(savedStar);
    }
}
