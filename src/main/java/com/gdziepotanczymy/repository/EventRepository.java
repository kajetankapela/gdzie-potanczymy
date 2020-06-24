package com.gdziepotanczymy.repository;

import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.model.Organizer;
import com.gdziepotanczymy.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByParticipantsNotContains(Participant participant);

    List<Event> findAllByOrganizerIsNot(Organizer organizer);

    List<Event> findAllByOrganizer(Organizer organizer);
}
