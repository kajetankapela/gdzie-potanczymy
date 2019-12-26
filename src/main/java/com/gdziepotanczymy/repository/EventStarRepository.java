package com.gdziepotanczymy.repository;

import com.gdziepotanczymy.model.EventStar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStarRepository extends JpaRepository<EventStar, Long> {
}
