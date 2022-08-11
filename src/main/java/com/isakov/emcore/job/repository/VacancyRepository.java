package com.isakov.emcore.job.repository;

import com.isakov.emcore.job.entity.VacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {
    List<VacancyEntity> findByTitle(String title);
}
