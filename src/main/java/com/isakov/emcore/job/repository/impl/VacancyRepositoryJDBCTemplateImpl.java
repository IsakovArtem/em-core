package com.isakov.emcore.job.repository.impl;

import com.isakov.emcore.job.entity.SalaryEntity;
import com.isakov.emcore.job.entity.SkillEntity;
import com.isakov.emcore.job.entity.VacancyEntity;
import com.isakov.emcore.job.repository.VacancyRepositoryJDBCTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class VacancyRepositoryJDBCTemplateImpl implements VacancyRepositoryJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcOperations jdbcOperations;

    @Override
    public List<VacancyEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM VACANCY", this::mapRowToVacancyEntity);
    }

    @Override
    public List<Map<String, Object>> findAllInListOfMap() {
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
        return jdbcTemplate.query("SELECT * FROM VACANCY WHERE id=? OR title=?",
                new BeanPropertyRowMapper<>(VacancyEntity.class),
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

    private VacancyEntity mapRowToVacancyEntity(ResultSet row, int rowNum) throws SQLException {
        //Simple example
        VacancyEntity vacancyEntity = new VacancyEntity();
        vacancyEntity.setId(row.getInt("id"));
        vacancyEntity.setTitle(row.getString("title"));
        vacancyEntity.setCompany(row.getString("company"));
        return vacancyEntity;
    }

    @Override
    @Transactional
    public void saveCascade(VacancyEntity vacancyEntity) {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "INSERT INTO VACANCY(title, company, salary_from, salary_to, currency, gross, create_date) " +
                                "VALUES(?, ?, ?, ?, ?, ?, ?)",
                        Types.VARCHAR, Types.VARCHAR, Types.NUMERIC, Types.NUMERIC, Types.VARCHAR, Types.BOOLEAN, Types.TIMESTAMP
                );
        pscf.setReturnGeneratedKeys(true);

        vacancyEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        SalaryEntity salaryEntity = vacancyEntity.getSalary();

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                vacancyEntity.getTitle(),
                                vacancyEntity.getCompany(),
                                salaryEntity.getFrom(),
                                salaryEntity.getTo(),
                                salaryEntity.getCurrency(),
                                salaryEntity.isGross(),
                                vacancyEntity.getCreateDate()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        vacancyEntity.setId((long) keyHolder.getKeys().get("id"));
        saveSkills(vacancyEntity.getSkills());
        saveVacancySkill(vacancyEntity);

    }

    private void saveSkills(List<SkillEntity> skillEntities) {

        for (SkillEntity skillEntity : skillEntities) {
            saveSkill(skillEntity);
        }
    }

    private void saveSkill(SkillEntity skillEntity) {

        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("INSERT INTO skill(name) VALUES(?)", Types.VARCHAR);
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(skillEntity.getName()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        skillEntity.setId((long) keyHolder.getKeys().get("id"));
    }

    private void saveVacancySkill(VacancyEntity vacancyEntity) {

        List<SkillEntity> skills = vacancyEntity.getSkills();

        jdbcTemplate.batchUpdate("INSERT INTO VACANCY_SKILL(vacancy_id, skill_id) VALUES(?, ?)",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, vacancyEntity.getId());
                        ps.setLong(2, skills.get(i).getId());
                    }

                    @Override
                    public int getBatchSize() {
                        return skills.size();
                    }
                });

    }

}
