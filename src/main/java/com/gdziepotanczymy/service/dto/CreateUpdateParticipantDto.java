package com.gdziepotanczymy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

//@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateUpdateParticipantDto extends CreateUpdateUserDto {
    private String surname;
    private String gender;
}
