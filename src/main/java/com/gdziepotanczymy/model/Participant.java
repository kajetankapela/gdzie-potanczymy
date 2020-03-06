package com.gdziepotanczymy.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Participant extends User {
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    private String surname;
    private String gender;

    @ManyToMany(mappedBy = "participants", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Event> events = new ArrayList<>();
}
