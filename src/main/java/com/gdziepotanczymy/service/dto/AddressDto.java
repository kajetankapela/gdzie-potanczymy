package com.gdziepotanczymy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Long id;
    private String country;
    private String postalCode;
    private String city;
    private String street;
    private String number;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
