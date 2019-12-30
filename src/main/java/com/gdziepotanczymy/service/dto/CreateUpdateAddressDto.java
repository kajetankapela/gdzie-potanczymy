package com.gdziepotanczymy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateAddressDto {
    private String country;
    private String postalCode;
    private String city;
    private String street;
    private String number;
}
