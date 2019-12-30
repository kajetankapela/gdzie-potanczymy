package com.gdziepotanczymy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateEventStarDto {
    private Long eventId;
    private Long starId;
}
