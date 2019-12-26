package com.gdziepotanczymy.service.dto;

import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.model.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventParticipantDto {
    private Long id;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Event event;
    private Participant participant;
}
