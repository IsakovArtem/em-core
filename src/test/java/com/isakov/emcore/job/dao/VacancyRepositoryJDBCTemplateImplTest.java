package com.isakov.emcore.job.dao;

import com.isakov.emcore.job.entity.SalaryEntity;
import com.isakov.emcore.job.entity.SkillEntity;
import com.isakov.emcore.job.entity.VacancyEntity;
import com.isakov.emcore.job.repository.VacancyRepositoryJDBCTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class VacancyRepositoryJDBCTemplateImplTest {

    public static final String TITLE1 = "title1";
    public static final String COMPANY1 = "company1";
    public static final String SKILL_NAME1 = "skillName1";
    public static final String SKILL_NAME2 = "skillName2";
    public static final String SKILL_NAME3 = "skillName3";
    public static final String CURRENCY1 = "currency1";

    @Autowired
    VacancyRepositoryJDBCTemplate vacancyRepositoryJDBCTemplate;

    @Test
    void index() {
        List<VacancyEntity> vacancyEntityList = vacancyRepositoryJDBCTemplate.findAll();
        System.out.println(vacancyEntityList);
    }

    @Test
    void index1() {
        List<Map<String, Object>> vacancyEntityList = vacancyRepositoryJDBCTemplate.findAllInListOfMap();
        System.out.println(vacancyEntityList);
    }

    @Test
    void findById() {

        List<Long> ids= new ArrayList<>();
        ids.add(1L);
        ids.add(3L);

        List<String> titles= new ArrayList<>();
        titles.add("test");
        titles.add("Javascript developer");

        List<VacancyEntity> vacancyEntityList = vacancyRepositoryJDBCTemplate.findByIdsAndTitles(titles, ids);
        System.out.println(vacancyEntityList);
    }

    @Test
    void findByIdAndTitle() {
        VacancyEntity vacancyEntity = vacancyRepositoryJDBCTemplate.findByIdAndTitle(2L, "test");
        System.out.println(vacancyEntity);
    }


    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void saveCascade() {

        List<SkillEntity> skills = new ArrayList<>();
        skills.add(SkillEntity.builder().name(SKILL_NAME1).build());
        skills.add(SkillEntity.builder().name(SKILL_NAME2).build());
        skills.add(SkillEntity.builder().name(SKILL_NAME3).build());

        VacancyEntity vacancyEntity = VacancyEntity.builder()
                .title(TITLE1)
                .company(COMPANY1)
                .salary(SalaryEntity.builder()
                        .from(1)
                        .to(100)
                        .currency(CURRENCY1)
                        .gross(true)
                        .build())
                .skills(skills)
                .build();

        vacancyRepositoryJDBCTemplate.saveCascade(vacancyEntity);

    }

}