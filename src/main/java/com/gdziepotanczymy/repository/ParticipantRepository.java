package com.gdziepotanczymy.repository;

import com.gdziepotanczymy.model.Event;
import com.gdziepotanczymy.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Participant findByLogin(String login);

    List<Participant> findAllByEventsContains(Event event);
}
