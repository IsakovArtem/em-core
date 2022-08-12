package com.isakov.emcore.job.repository;

import com.isakov.emcore.job.entity.VacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {
    List<VacancyEntity> findByTitle(String title);

    @Query(value = "SELECT * FROM vacancy WHERE salary_from >= :salary_from and salary_to <= :salary_to", nativeQuery = true)
    List<VacancyEntity> SearchBySalaryRange(@Param("salary_from") int salary_from, @Param("salary_to") int salary_to);
}
