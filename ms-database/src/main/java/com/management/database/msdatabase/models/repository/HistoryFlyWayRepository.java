package com.management.database.msdatabase.models.repository;

import com.management.database.msdatabase.models.jpa.HistoryFlyWayJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryFlyWayRepository extends JpaRepository<HistoryFlyWayJpa, Integer> {
 
}
