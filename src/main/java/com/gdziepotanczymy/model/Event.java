package com.gdziepotanczymy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "startDate")
    private String startDate;
    @Column(name = "endDate")
    private String endDate;
    @Column(name = "description")
    private String description;
    @Column(name = "comments")
    private String comments;
    @Column(name = "createdAt")
    private OffsetDateTime createdAt;
    @Column(name = "updatedAt")
    private OffsetDateTime updatedAt;

    @Embedded
    private Address address;
    @Embedded
    private NumberOfSeats numberOfSeats;

    @ManyToMany
    @Builder.Default
    private List<DanceType> danceTypes = new ArrayList<>();

    @ManyToMany
    @Builder.Default
    private List<Star> stars = new ArrayList<>();

    @ManyToMany
    @Builder.Default
    private List<Participant> participants = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Organizer organizer;
}

