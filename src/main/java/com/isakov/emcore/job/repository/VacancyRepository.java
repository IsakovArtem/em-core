package com.isakov.emcore.job.repository;

import com.isakov.emcore.job.entity.VacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {
    List<VacancyEntity> findByTitle(String title);
}
