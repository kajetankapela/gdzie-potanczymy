package com.gdziepotanczymy.service.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

//@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ParticipantDto extends UserDto{
//    private Long id;
    private String surname;
    private String gender;

    @Builder.Default
    private List<EventDto> events = new ArrayList<>();

}
