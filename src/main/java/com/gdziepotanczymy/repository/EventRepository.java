package com.gdziepotanczymy.repository;

import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.service.dto.EventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizerId(Long id);
}
