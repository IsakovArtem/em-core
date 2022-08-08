package com.isakov.emcore.job.service.impl;

import com.isakov.emcore.job.dto.headhunter.vacancies.HeadHunterVacancyDTO;
import com.isakov.emcore.job.service.HeadHunterApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class HeadHunterApiServiceImplTest {

    @Autowired
    private HeadHunterApiService headHunterApiService;

    @Test
    public void testHeadHunterGetVacancies() {
        Assertions.assertDoesNotThrow(() -> {
            List<HeadHunterVacancyDTO> vacancies = headHunterApiService.getVacancies("Java", "0", "3");
            Assertions.assertEquals(3, vacancies.size());
        });
    }

    @Test
    public void testHeadHunterGetVacancy() {
        Assertions.assertDoesNotThrow(() -> {
            //Ищем одну вакансию и запрашиваем её полностью по id
            HeadHunterVacancyDTO headHunterVacancy = headHunterApiService.getVacancy(
                    headHunterApiService.getVacancies("java", "0", "1").get(0).getId()
            );
            Assertions.assertNotNull(headHunterVacancy.getName());
            System.out.println(headHunterVacancy.getName());
            System.out.println(headHunterVacancy.getKeySkills().toString());
        });
    }
}