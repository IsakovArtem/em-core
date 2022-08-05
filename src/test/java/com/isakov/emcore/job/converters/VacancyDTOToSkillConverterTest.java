package com.isakov.emcore.job.converters;

import com.isakov.emcore.job.dto.VacancyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VacancyDTOToSkillConverterTest {

    @Autowired
    VacancyDTOToSkillConverter vacancyDTOToSkillConverter;

    @Test
    void convert() {

        List<VacancyDTO> vacancies = new ArrayList<>();
        VacancyDTO vacancy1 = new VacancyDTO();
        vacancy1.setCompany("Google");
        vacancy1.setTitle("Software Developer");
        vacancy1.setSkills(Set.of("Java", "Spring", "Git", "SQL"));
        vacancies.add(vacancy1);

        VacancyDTO vacancy2 = new VacancyDTO();
        vacancy2.setCompany("Yandex");
        vacancy2.setTitle("Senior Java Developer");
        vacancy2.setSkills(Set.of("Java", "Spring", "SQL"));
        vacancies.add(vacancy2);

        VacancyDTO vacancy3 = new VacancyDTO();
        vacancy3.setCompany("SAP");
        vacancy3.setTitle("Engineer");
        vacancy3.setSkills(Set.of("SQL"));
        vacancies.add(vacancy3);

        Map<String, Integer> map = vacancyDTOToSkillConverter.convert(vacancies);
        assertEquals(3, map.get("SQL"));
        assertEquals(2, map.get("Java"));
        assertEquals(2, map.get("Spring"));
        assertEquals(1, map.get("Git"));

    }
}