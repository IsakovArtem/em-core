package com.isakov.emcore.job.dao;

import com.isakov.emcore.job.entity.SalaryEntity;
import com.isakov.emcore.job.entity.VacancyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<VacancyEntity> index() {
        return jdbcTemplate.query("SELECT * FROM VACANCY", new BeanPropertyRowMapper<>(VacancyEntity.class));
    }

    public VacancyEntity show(int id) {
        return jdbcTemplate.query("SELECT * FROM VACANCY WHERE id=?", new BeanPropertyRowMapper<>(VacancyEntity.class),
                new Object[]{id}).stream().findAny().orElse(null);
    }

    public void save(VacancyEntity vacancyEntity) {

        SalaryEntity salaryEntity = vacancyEntity.getSalary();

        jdbcTemplate.update("INSERT INTO VACANCY(title, company, salary_from, salary_to, currency, gross, create_date) VALUES(?, ?, ?)",
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
