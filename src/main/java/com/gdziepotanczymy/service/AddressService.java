package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.Address;
import com.gdziepotanczymy.repository.AddressRepository;
import com.gdziepotanczymy.service.dto.AddressDto;
import com.gdziepotanczymy.service.dto.CreateUpdateAddressDto;
import com.gdziepotanczymy.service.mapper.AddressDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;
    private final AddressDtoMapper mapper;

    @Transactional
    public List<AddressDto> getAllAddresses() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AddressDto getAddressById(Long id) throws NotFound {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public AddressDto createAddress(CreateUpdateAddressDto createUpdateAddressDto) {
        Address address = Address.builder()
                .country(createUpdateAddressDto.getCountry())
                .postalCode(createUpdateAddressDto.getPostalCode())
                .city(createUpdateAddressDto.getCity())
                .street(createUpdateAddressDto.getStreet())
                .number(createUpdateAddressDto.getNumber())
                .createdAt(OffsetDateTime.now())
                .build();

        Address savedAddress = repository.save(address);

        return mapper.toDto(savedAddress);
    }
}
