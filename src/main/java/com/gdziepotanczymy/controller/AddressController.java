package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.service.AddressService;
import com.gdziepotanczymy.service.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
