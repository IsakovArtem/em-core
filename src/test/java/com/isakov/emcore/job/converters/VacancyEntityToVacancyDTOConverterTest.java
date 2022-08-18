package com.isakov.emcore.job.converters;

import com.isakov.emcore.job.dto.VacancyDTO;
import com.isakov.emcore.job.entity.SalaryEntity;
import com.isakov.emcore.job.entity.SkillEntity;
import com.isakov.emcore.job.entity.VacancyEntity;
import com.isakov.emcore.job.repository.VacancyRepository;
import lombok.NonNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VacancyEntityToVacancyDTOConverterTest {

//    public static final String TITLE = "title1";
//    public static final String COMPANY = "company1";
//    public static final String SKILL_NAME = "skillName1";
//
//    @Autowired
//    VacancyEntityToVacancyDTOConverter vacancyEntityToVacancyDTOConverter;
//
//    @Test
//    void convert() {
//
//        List<SkillEntity> skills = new ArrayList<>();
//        skills.add(SkillEntity.builder().name(SKILL_NAME).build());
//
//        List<VacancyEntity> vacancies = new ArrayList<>();
//        vacancies.add(VacancyEntity.builder()
//                .title(TITLE)
//                .company(COMPANY)
//                .salary(SalaryEntity.builder()
//                        .from(1)
//                        .to(100)
//                        .currency("currency1")
//                        .gross(true)
//                        .build())
//                .createDate(new Timestamp(System.currentTimeMillis()))
//                .skills(skills)
//                .build());
//
//        List<VacancyDTO> vacanciesDTO = vacancyEntityToVacancyDTOConverter.convert(vacancies);
//        assertEquals(TITLE, vacanciesDTO.get(0).getTitle());
//        assertEquals(COMPANY, vacanciesDTO.get(0).getCompany());
//        assertEquals(1, vacanciesDTO.get(0).getSkills().size());
//    }
}