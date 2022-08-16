package com.isakov.emcore.job.repository;

import com.isakov.emcore.job.entity.SalaryEntity;
import com.isakov.emcore.job.entity.VacancyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class VacancyDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<VacancyEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM VACANCY", new BeanPropertyRowMapper<>(VacancyEntity.class));
    }
    public List<Map<String, Object>> findAllInList() {
        return jdbcTemplate.queryForList("SELECT * FROM VACANCY");
    }

    public List<VacancyEntity> findByIdsAndTitles(List<String> titles, List<Long> ids) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ids", ids);
        parameters.put("titles", titles);

        return namedJdbcTemplate.query("SELECT * FROM VACANCY WHERE id IN (:ids) AND title IN (:titles)", parameters,
                new BeanPropertyRowMapper<>(VacancyEntity.class));
    }

    public VacancyEntity findByIdAndTitle(Long id, String title) {
        return jdbcTemplate.query("SELECT * FROM VACANCY WHERE id=? OR title=?", new BeanPropertyRowMapper<>(VacancyEntity.class),
                new Object[]{id, title}).stream().findAny().orElse(null);
    }

    public void save(VacancyEntity vacancyEntity) {

        SalaryEntity salaryEntity = vacancyEntity.getSalary();

        jdbcTemplate.update("INSERT INTO VACANCY(title, company, salary_from, salary_to, currency, gross, create_date) " +
                                "VALUES(?, ?, ?, ?, ?, ?, ?)",
                vacancyEntity.getTitle(), vacancyEntity.getCompany(), salaryEntity.getFrom(),
                salaryEntity.getTo(), salaryEntity.getCurrency(), vacancyEntity.getCreateDate());
    }

    public void update(int id, VacancyEntity vacancyEntity) {
        jdbcTemplate.update("UPDATE VACANCY SET title=?, company=? WHERE id=?", vacancyEntity.getTitle(),
                vacancyEntity.getCompany(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM VACANCY WHERE id=?", id);
    }

}
