package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.AlreadyExists;
import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Address;
import com.gdziepotanczymy.model.Organizer;
import com.gdziepotanczymy.repository.OrganizerRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateOrganizerDto;
import com.gdziepotanczymy.service.dto.OrganizerDto;
//import com.gdziepotanczymy.service.mapper.AddressModelMapper;
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
    private final OrganizerDtoMapper organizerDtoMapper;
//    private final AddressModelMapper addressModelMapper;

    @Transactional
    public List<OrganizerDto> getAllOrganizers() {
        return repository.findAll()
                .stream()
                .map(organizerDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrganizerDto getOrganizerById(Long id) throws NotFound {
        return repository.findById(id)
                .map(organizerDtoMapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public OrganizerDto createOrganizer(CreateUpdateOrganizerDto createUpdateOrganizerDto) throws BadRequest, AlreadyExists {
//        isOrganizerOk(createUpdateOrganizerDto);

        Address address = Address.builder()
                .country(createUpdateOrganizerDto.getCreateUpdateAddressDto().getCountry())
                .postalCode(createUpdateOrganizerDto.getCreateUpdateAddressDto().getPostalCode())
                .city(createUpdateOrganizerDto.getCreateUpdateAddressDto().getCity())
                .street(createUpdateOrganizerDto.getCreateUpdateAddressDto().getStreet())
                .number(createUpdateOrganizerDto.getCreateUpdateAddressDto().getNumber())
                .build();

        Organizer organizer = Organizer.builder()
                .name(createUpdateOrganizerDto.getName())
                .login(createUpdateOrganizerDto.getLogin())
                .password(createUpdateOrganizerDto.getPassword())
                .email(createUpdateOrganizerDto.getEmail())
                .phoneNumber(createUpdateOrganizerDto.getPhoneNumber())
                .address(address)
                .createdAt(OffsetDateTime.now())
                .build();

        Organizer savedOrganizer = repository.save(organizer);

        return organizerDtoMapper.toDto(savedOrganizer);
    }

    @Transactional
    public OrganizerDto updateOrganizerById(Long id, CreateUpdateOrganizerDto createUpdateOrganizerDto) throws NotFound, BadRequest, AlreadyExists {
//        isOrganizerOk(createUpdateOrganizerDto);

        Organizer existingOrganizer = repository.findById(id).orElseThrow(NotFound::new);

        Address address = Address.builder()
                .country(createUpdateOrganizerDto.getCreateUpdateAddressDto().getCountry())
                .postalCode(createUpdateOrganizerDto.getCreateUpdateAddressDto().getPostalCode())
                .city(createUpdateOrganizerDto.getCreateUpdateAddressDto().getCity())
                .street(createUpdateOrganizerDto.getCreateUpdateAddressDto().getStreet())
                .number(createUpdateOrganizerDto.getCreateUpdateAddressDto().getNumber())
                .build();

        existingOrganizer.setName(createUpdateOrganizerDto.getName());
        existingOrganizer.setLogin(createUpdateOrganizerDto.getLogin());
        existingOrganizer.setPassword(createUpdateOrganizerDto.getPassword());
        existingOrganizer.setEmail(createUpdateOrganizerDto.getEmail());
        existingOrganizer.setPhoneNumber(createUpdateOrganizerDto.getPhoneNumber());
        existingOrganizer.setAddress(address);
        existingOrganizer.setUpdatedAt(OffsetDateTime.now());

        Organizer savedOrganizer = repository.save(existingOrganizer);

        return organizerDtoMapper.toDto(savedOrganizer);
    }

    @Transactional
    public OrganizerDto deleteOrganizerById(Long id) throws NotFound {
        Organizer existingOrganizer = repository.findById(id).orElseThrow(NotFound::new);

        repository.delete(existingOrganizer);

        return organizerDtoMapper.toDto(existingOrganizer);
    }

//    private void isOrganizerOk(CreateUpdateOrganizerDto createUpdateOrganizerDto) throws BadRequest, AlreadyExists {
//        if (createUpdateOrganizerDto.getName() == null || createUpdateOrganizerDto.getName().isEmpty()) {
//            throw new BadRequest();
//        }
//
//        if (repository.existsByName(createUpdateOrganizerDto.getName())) {
//            throw new AlreadyExists();
//        }
//    }
}
