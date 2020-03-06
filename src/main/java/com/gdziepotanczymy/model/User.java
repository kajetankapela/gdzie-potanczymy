package com.gdziepotanczymy.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
//@RequiredArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected String login;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    @Embedded
    protected Address address;
}
