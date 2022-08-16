package com.isakov.emcore.job.repository;

import com.isakov.emcore.job.entity.VacancyEntity;

import java.util.List;
import java.util.Map;

public interface VacancyRepositoryJDBCTemplate {

    List<VacancyEntity> findAll();
    List<Map<String, Object>> findAllInListOfMap();

    List<VacancyEntity> findByIdsAndTitles(List<String> titles, List<Long> ids);

    VacancyEntity findByIdAndTitle(Long id, String title);

    void save(VacancyEntity vacancyEntity);

    void update(int id, VacancyEntity vacancyEntity);

    void delete(int id);

    void saveCascade(VacancyEntity vacancyEntity);
}
