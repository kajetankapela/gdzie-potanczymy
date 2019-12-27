package com.gdziepotanczymy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_dance_type")
public class EventDanceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    @OneToOne
    private Event event;
    @OneToOne
    private DanceType danceType;

}