package com.gdziepotanczymy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SuperBuilder
public class Admin extends User {

}
