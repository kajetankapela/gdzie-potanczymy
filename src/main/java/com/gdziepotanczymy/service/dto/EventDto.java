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
public class EventDto {
    private Long id;
    private String name;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String description;
    private Long addressId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
