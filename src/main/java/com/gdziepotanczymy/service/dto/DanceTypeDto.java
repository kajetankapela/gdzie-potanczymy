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
public class DanceTypeDto {
    private Long id;
    private String name;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}