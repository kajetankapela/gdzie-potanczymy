package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Address;
import com.gdziepotanczymy.model.Participant;
import com.gdziepotanczymy.repository.ParticipantRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateParticipantDto;
import com.gdziepotanczymy.service.dto.ParticipantDto;
//import com.gdziepotanczymy.service.mapper.AddressModelMapper;
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
    private final ParticipantDtoMapper participantDtoMapper;
//    private final AddressModelMapper addressModelMapper;

    @Transactional
    public List<ParticipantDto> getAllParticipants() {
        return repository.findAll()
                .stream()
                .map(participantDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ParticipantDto getParticipantById(Long id) throws NotFound {
        return repository.findById(id)
                .map(participantDtoMapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public ParticipantDto createParticipant(CreateUpdateParticipantDto createUpdateParticipantDto) throws BadRequest {
//        isParticipantOk(createUpdateParticipantDto);

        Address address = Address.builder()
                .country(createUpdateParticipantDto.getCreateUpdateAddressDto().getCountry())
                .postalCode(createUpdateParticipantDto.getCreateUpdateAddressDto().getPostalCode())
                .city(createUpdateParticipantDto.getCreateUpdateAddressDto().getCity())
                .street(createUpdateParticipantDto.getCreateUpdateAddressDto().getStreet())
                .number(createUpdateParticipantDto.getCreateUpdateAddressDto().getNumber())
                .build();

        Participant participant = Participant.builder()
                .name(createUpdateParticipantDto.getName())
                .surname(createUpdateParticipantDto.getSurname())
                .gender(createUpdateParticipantDto.getGender())
                .login(createUpdateParticipantDto.getLogin())
                .password(createUpdateParticipantDto.getPassword())
                .email(createUpdateParticipantDto.getEmail())
                .phoneNumber(createUpdateParticipantDto.getPhoneNumber())
                .address(address)
                .createdAt(OffsetDateTime.now())
                .build();

        Participant savedParticipant = repository.save(participant);

        return participantDtoMapper.toDto(savedParticipant);
    }

    @Transactional
    public ParticipantDto updateParticipantById(Long id, CreateUpdateParticipantDto createUpdateParticipantDto) throws NotFound, BadRequest {
//        isParticipantOk(createUpdateParticipantDto);

        Participant existingParticipant = repository.findById(id).orElseThrow(NotFound::new);

        Address address = Address.builder()
                .country(createUpdateParticipantDto.getCreateUpdateAddressDto().getCountry())
                .postalCode(createUpdateParticipantDto.getCreateUpdateAddressDto().getPostalCode())
                .city(createUpdateParticipantDto.getCreateUpdateAddressDto().getCity())
                .street(createUpdateParticipantDto.getCreateUpdateAddressDto().getStreet())
                .number(createUpdateParticipantDto.getCreateUpdateAddressDto().getNumber())
                .build();

        existingParticipant.setName(createUpdateParticipantDto.getName());
        existingParticipant.setSurname(createUpdateParticipantDto.getSurname());
        existingParticipant.setGender(createUpdateParticipantDto.getGender());
        existingParticipant.setLogin(createUpdateParticipantDto.getLogin());
        existingParticipant.setPassword(createUpdateParticipantDto.getPassword());
        existingParticipant.setEmail(createUpdateParticipantDto.getEmail());
        existingParticipant.setPhoneNumber(createUpdateParticipantDto.getPhoneNumber());
        existingParticipant.setAddress(address);
        existingParticipant.setUpdatedAt(OffsetDateTime.now());

        Participant savedParticipant = repository.save(existingParticipant);

        return participantDtoMapper.toDto(savedParticipant);
    }

    @Transactional
    public ParticipantDto deleteParticipantById(Long id) throws NotFound {
        Participant existingParticipant = repository.findById(id).orElseThrow(NotFound::new);

        repository.delete(existingParticipant);

        return participantDtoMapper.toDto(existingParticipant);
    }

//    private void isParticipantOk(CreateUpdateParticipantDto createUpdateParticipantDto) throws BadRequest {
//        if (createUpdateParticipantDto.getName() == null || createUpdateParticipantDto.getName().isEmpty()
//                || createUpdateParticipantDto.getSurname() == null || createUpdateParticipantDto.getSurname().isEmpty()
//        ) {
//            throw new BadRequest();
//        }
//    }
}
