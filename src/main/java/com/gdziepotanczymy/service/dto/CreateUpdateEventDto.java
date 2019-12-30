package com.gdziepotanczymy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateEventDto {
    private String name;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String description;
    private Long addressId;
}
