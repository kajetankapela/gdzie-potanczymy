package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Participant;
import com.gdziepotanczymy.repository.ParticipantRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateParticipantDto;
import com.gdziepotanczymy.service.dto.ParticipantDto;
import com.gdziepotanczymy.service.mapper.ParticipantDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository repository;
    private final ParticipantDtoMapper mapper;

    @Transactional
    public List<ParticipantDto> getAllParticipants() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ParticipantDto getParticipantById(Long id) throws NotFound {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public ParticipantDto createParticipant(CreateUpdateParticipantDto createUpdateParticipantDto) throws BadRequest {
        isParticipantOk(createUpdateParticipantDto);

        Participant participant = Participant.builder()
                .name(createUpdateParticipantDto.getName())
                .surname(createUpdateParticipantDto.getSurname())
                .createdAt(OffsetDateTime.now())
                .build();

        Participant savedParticipant = repository.save(participant);

        return mapper.toDto(savedParticipant);
    }

    @Transactional
    public ParticipantDto updateParticipantById(Long id, CreateUpdateParticipantDto createUpdateParticipantDto) throws NotFound, BadRequest {
        isParticipantOk(createUpdateParticipantDto);

        Participant existingParticipant = repository.findById(id).orElseThrow(NotFound::new);

        existingParticipant.setName(createUpdateParticipantDto.getName());
        existingParticipant.setSurname(createUpdateParticipantDto.getSurname());
        existingParticipant.setUpdatedAt(OffsetDateTime.now());

        Participant savedParticipant = repository.save(existingParticipant);

        return mapper.toDto(savedParticipant);
    }

    @Transactional
    public ParticipantDto deleteParticipantById(Long id) throws NotFound {
        Participant existingParticipant = repository.findById(id).orElseThrow(NotFound::new);

        repository.delete(existingParticipant);

        return mapper.toDto(existingParticipant);
    }

    private void isParticipantOk(CreateUpdateParticipantDto createUpdateParticipantDto) throws BadRequest {
        if (createUpdateParticipantDto.getName() == null || createUpdateParticipantDto.getName().isEmpty()
                || createUpdateParticipantDto.getSurname() == null || createUpdateParticipantDto.getSurname().isEmpty()
        ) {
            throw new BadRequest();
        }
    }
}
