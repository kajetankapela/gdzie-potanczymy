package com.gdziepotanczymy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private String description;
    private String comments;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    @Embedded
    private AddressDto address;
    private NumberOfSeatsDto numberOfSeats;

    @Builder.Default
    private List<DanceTypeDto> danceTypes = new ArrayList<>();

    @Builder.Default
    private List<StarDto> stars = new ArrayList<>();

    @Builder.Default
    private List<ParticipantDto> participants = new ArrayList<>();

    @Builder.Default
    private OrganizerDto organizer = new OrganizerDto();
}