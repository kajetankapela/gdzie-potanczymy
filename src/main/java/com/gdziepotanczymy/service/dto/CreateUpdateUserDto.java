package com.gdziepotanczymy.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
//@RequiredArgsConstructor
public abstract class CreateUpdateUserDto {
    @Size(min = 3)
    protected String name;
    @Size(min = 3)
    protected String login;
    @Size(min=3)
    protected String password;
    @Email
    protected String email;
    protected String phoneNumber;

    @Embedded
    protected CreateUpdateAddressDto createUpdateAddressDto;
}
