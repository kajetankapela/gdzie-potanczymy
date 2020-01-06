package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.AddressService;
import com.gdziepotanczymy.service.dto.AddressDto;
import com.gdziepotanczymy.service.dto.CreateUpdateAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping()
    public List<AddressDto> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public AddressDto getAddressById(@PathVariable Long id) throws NotFound {
        return addressService.getAddressById(id);
    }

    @PostMapping()
    public AddressDto newAddress(@RequestBody CreateUpdateAddressDto createUpdateAddressDto) throws BadRequest {
        return addressService.createAddress(createUpdateAddressDto);
    }

    @PutMapping("/{id}")
    public AddressDto updateAddressById(@PathVariable Long id, @RequestBody CreateUpdateAddressDto createUpdateAddressDto) throws NotFound, BadRequest {
        return addressService.updateAddressById(id, createUpdateAddressDto);
    }

    @DeleteMapping("/{id}")
    public AddressDto deleteAddressById(@PathVariable Long id) throws NotFound {
        return addressService.deleteAddressById(id);
    }
}
