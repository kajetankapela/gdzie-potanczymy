package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.AlreadyExists;
import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.DanceType;
import com.gdziepotanczymy.repository.DanceTypeRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateDanceTypeDto;
import com.gdziepotanczymy.service.dto.DanceTypeDto;
import com.gdziepotanczymy.service.mapper.DanceTypeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DanceTypeService {
    private final DanceTypeRepository repository;
    private final DanceTypeDtoMapper mapper;

    @Transactional
    public List<DanceTypeDto> getAllDanceTypes() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public DanceTypeDto getDanceTypeById(Long id) throws NotFound {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public DanceTypeDto createDanceType(CreateUpdateDanceTypeDto createUpdateDanceTypeDto) throws BadRequest, AlreadyExists {

        DanceType danceType = DanceType.builder()
                .name(createUpdateDanceTypeDto.getName())
                .description(createUpdateDanceTypeDto.getDescription())
                .comments(createUpdateDanceTypeDto.getComments())
                .createdAt(OffsetDateTime.now())
                .build();

        DanceType savedDanceType = repository.save(danceType);

        return mapper.toDto(savedDanceType);
    }

    @Transactional
    public DanceTypeDto updateDanceTypeById(Long id, CreateUpdateDanceTypeDto createUpdateDanceTypeDto) throws NotFound, BadRequest, AlreadyExists {

        DanceType existingDanceType = repository.findById(id).orElseThrow(NotFound::new);

        existingDanceType.setName(createUpdateDanceTypeDto.getName());
        existingDanceType.setDescription(createUpdateDanceTypeDto.getDescription());
        existingDanceType.setComments(createUpdateDanceTypeDto.getComments());
        existingDanceType.setUpdatedAt(OffsetDateTime.now());

        DanceType savedDanceType = repository.save(existingDanceType);

        return mapper.toDto(savedDanceType);
    }

    @Transactional
    public DanceTypeDto deleteDanceTypeById(Long id) throws NotFound {
        DanceType existingDanceType = repository.findById(id).orElseThrow(NotFound::new);

        repository.delete(existingDanceType);

        return mapper.toDto(existingDanceType);
    }

}
