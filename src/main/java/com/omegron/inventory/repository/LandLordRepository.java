package com.omegron.inventory.repository;

import com.omegron.inventory.model.entity.LandLord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandLordRepository extends JpaRepository<LandLord, Long> {
}
