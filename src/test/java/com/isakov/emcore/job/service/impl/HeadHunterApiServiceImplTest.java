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
            List<HeadHunterVacancyDTO> vacancies = headHunterApiService.getVacancies("Java", "0", "2");
            Assertions.assertEquals(2, vacancies.size());

            debugInfo(vacancies);
        });
    }

    private void debugInfo(List<HeadHunterVacancyDTO> vacancies) {
        for (HeadHunterVacancyDTO vacancy : vacancies) {
            System.out.print(vacancy.getName() + " / " + vacancy.getEmployer().getName() + " / ");
            if (vacancy.getSalary() != null) {
                if (vacancy.getSalary().getFrom() != null) {
                    System.out.print(vacancy.getSalary().getFrom());
                } else {
                    System.out.print("_");
                }
                System.out.print(" - ");
                if (vacancy.getSalary().getTo() != null) {
                    System.out.println(vacancy.getSalary().getTo());
                } else {
                    System.out.print("_");
                }
            } else {
                System.out.println("_");
            }

            if (vacancy.getKeySkills() != null) {
                vacancy.getKeySkills().forEach(System.out::println);
            }
        }
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