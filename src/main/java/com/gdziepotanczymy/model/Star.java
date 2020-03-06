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
@Table(name = "star")
public class Star {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "description")
    private String description;
    @Column(name = "comments")
    private String comments;
    @Column(name = "createdAt")
    private OffsetDateTime createdAt;
    @Column(name = "updatedAt")
    private OffsetDateTime updatedAt;

    @ManyToMany(mappedBy = "stars", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Event> events = new ArrayList<>();
}
