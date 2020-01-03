package com.gdziepotanczymy.service.dto;

import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.model.Star;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventStarDto {
    private Long id;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Long eventId;
    private Long starId;
}
