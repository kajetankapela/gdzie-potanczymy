package com.gdziepotanczymy.repository;

import com.gdziepotanczymy.model.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, Long> {
}
