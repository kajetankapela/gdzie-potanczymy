package com.gdziepotanczymy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;

@Data
@Embeddable
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
//@MappedSuperclass
public class AddressDto {
    protected String country;
    protected String postalCode;
    protected String city;
    protected String street;
    protected String number;
}
