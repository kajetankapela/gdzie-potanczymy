package com.gdziepotanczymy.service.dto;

import com.gdziepotanczymy.model.DanceType;
import com.gdziepotanczymy.model.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDanceTypeDto {
    private Long id;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Long eventId;
    private Long danceTypeId;
}
