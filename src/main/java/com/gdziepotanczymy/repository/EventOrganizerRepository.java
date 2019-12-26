package com.gdziepotanczymy.repository;

import com.gdziepotanczymy.model.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventOrganizerRepository extends JpaRepository<EventOrganizer, Long> {
}
