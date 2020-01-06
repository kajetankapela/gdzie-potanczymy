package com.gdziepotanczymy.repository;

import com.gdziepotanczymy.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    boolean existsByName(String name);
}
