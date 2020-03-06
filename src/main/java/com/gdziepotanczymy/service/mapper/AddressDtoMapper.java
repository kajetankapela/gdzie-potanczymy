package com.gdziepotanczymy.service.mapper;

import com.gdziepotanczymy.model.Address;
import com.gdziepotanczymy.service.dto.AddressDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AddressDtoMapper {
    @Transactional
    public AddressDto toDto(Address address) {
        return AddressDto.builder()
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .city(address.getCity())
                .street(address.getStreet())
                .number(address.getNumber())
                .build();
    }
}
