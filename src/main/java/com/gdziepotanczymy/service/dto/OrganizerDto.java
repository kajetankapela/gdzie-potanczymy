package com.gdziepotanczymy.service.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

//@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrganizerDto extends UserDto{
//    private Long id;

    @Builder.Default
    private List<EventDto> events = new ArrayList<>();
}
