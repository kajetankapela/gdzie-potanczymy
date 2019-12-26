package com.gdziepotanczymy.service;

import com.gdziepotanczymy.repository.AddressRepository;
import com.gdziepotanczymy.service.dto.AddressDto;
import com.gdziepotanczymy.service.mapper.AddressDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;
    private final AddressDtoMapper mapper;

    public List<AddressDto> getAllAddresses() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
