package com.gdziepotanczymy.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Organizer extends User {
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Event> events = new ArrayList<>();
}
