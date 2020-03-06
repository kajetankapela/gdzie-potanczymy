package com.gdziepotanczymy.repository;

import com.gdziepotanczymy.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
//    boolean existsByName(String name);
}
