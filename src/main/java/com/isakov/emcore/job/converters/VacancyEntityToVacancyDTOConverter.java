package com.isakov.emcore.job.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isakov.emcore.job.dto.SalaryDTO;
import com.isakov.emcore.job.dto.VacancyDTO;
import com.isakov.emcore.job.entity.SkillEntity;
import com.isakov.emcore.job.entity.VacancyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VacancyEntityToVacancyDTOConverter implements GenericConverter<List<VacancyEntity>, List<VacancyDTO>> {
    private final ObjectMapper mapper;

    @Override
    public List<VacancyDTO> convert(List<VacancyEntity> vacancyEntities) {
        List<VacancyDTO> vacanciesDTO = new ArrayList<>();
        for (VacancyEntity entity : vacancyEntities) {
            vacanciesDTO.add(VacancyDTO.builder()
                    .company(entity.getCompany())
                    .title(entity.getTitle())
                    .salary(mapper.convertValue(entity.getSalary(), SalaryDTO.class))
                    .skills(entity.getSkills().stream()
                            .map(SkillEntity::getName)
                            .collect(Collectors.toSet()))
                    .build());
        }
        return vacanciesDTO;
    }
}
