package com.gdziepotanczymy.service.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrganizerDto extends UserDto {

    @Builder.Default
    private List<EventDto> events = new ArrayList<>();
}
