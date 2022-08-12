package com.isakov.emcore.job.dao;

import com.isakov.emcore.job.entity.VacancyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class VacancyDAOTest {

    @Autowired
    VacancyDAO vacancyDAO;

    @Test
    void index() {
        List<VacancyEntity> vacancyEntityList = vacancyDAO.findAll();
        System.out.println(vacancyEntityList);
    }

    @Test
    void index1() {
        List<Map<String, Object>> vacancyEntityList = vacancyDAO.findAllInList();
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

        List<VacancyEntity> vacancyEntityList = vacancyDAO.findByIdsAndTitles(titles, ids);
        System.out.println(vacancyEntityList);
    }

    @Test
    void findByIdAndTitle() {
        VacancyEntity vacancyEntity = vacancyDAO.findByIdAndTitle(2L, "test");
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
}