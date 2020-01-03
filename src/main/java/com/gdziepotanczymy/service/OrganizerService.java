package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Organizer;
import com.gdziepotanczymy.repository.OrganizerRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateOrganizerDto;
import com.gdziepotanczymy.service.dto.OrganizerDto;
import com.gdziepotanczymy.service.mapper.OrganizerDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizerService {
    private final OrganizerRepository repository;
    private final OrganizerDtoMapper mapper;

    @Transactional
    public List<OrganizerDto> getAllOrganizers() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrganizerDto getOrganizerById(Long id) throws NotFound {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public OrganizerDto createOrganizer(CreateUpdateOrganizerDto createUpdateOrganizerDto) {
        Organizer organizer = Organizer.builder()
                .name(createUpdateOrganizerDto.getName())
                .createdAt(OffsetDateTime.now())
                .build();

        Organizer savedOrganizer = repository.save(organizer);

        return mapper.toDto(savedOrganizer);
    }
}
