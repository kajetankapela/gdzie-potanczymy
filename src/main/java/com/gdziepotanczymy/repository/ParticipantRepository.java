package com.gdziepotanczymy.repository;

import com.gdziepotanczymy.model.Participant;
import com.gdziepotanczymy.service.dto.ParticipantDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Participant findByLogin(String login);
}
