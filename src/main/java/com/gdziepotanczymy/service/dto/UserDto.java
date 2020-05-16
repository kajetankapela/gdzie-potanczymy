package com.gdziepotanczymy.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embedded;
import java.time.OffsetDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class UserDto {
    protected Long id;
    protected String name;
    protected String login;
    protected String email;
    protected String phoneNumber;
    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    @Embedded
    protected AddressDto addressDto;
}
