package com.isakov.emcore.job.dao;

import com.isakov.emcore.job.entity.VacancyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VacancyDAOTest {

    @Autowired
    VacancyDAO vacancyDAO;

    @Test
    void index() {
        List<VacancyEntity> vacancyEntityList = vacancyDAO.index();
        System.out.println(vacancyEntityList);
    }

    @Test
    void show() {
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