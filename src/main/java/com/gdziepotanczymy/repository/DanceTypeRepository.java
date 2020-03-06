package com.gdziepotanczymy.repository;

import com.gdziepotanczymy.model.DanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanceTypeRepository extends JpaRepository<DanceType, Long> {
}
