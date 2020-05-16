package com.gdziepotanczymy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Data
@Embeddable
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateAddressDto {
    @Size(min = 3)
    protected String country;
    protected String postalCode;
    protected String city;
    protected String street;
    protected String number;
}
