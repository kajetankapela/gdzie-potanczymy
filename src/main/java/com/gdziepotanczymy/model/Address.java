package com.gdziepotanczymy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String country;
    private String postalCode;
    private String city;
    private String street;
    private String number;
}