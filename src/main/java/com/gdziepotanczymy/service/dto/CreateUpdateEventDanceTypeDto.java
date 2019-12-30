package com.gdziepotanczymy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateEventDanceTypeDto {
    private Long eventId;
    private Long danceTypeId;
}
