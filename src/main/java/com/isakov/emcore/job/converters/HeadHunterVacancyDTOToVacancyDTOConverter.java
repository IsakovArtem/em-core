package com.isakov.emcore.job.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isakov.emcore.job.dto.SalaryDTO;
import com.isakov.emcore.job.dto.VacancyDTO;
import com.isakov.emcore.job.dto.headhunter.vacancies.HeadHunterVacancyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HeadHunterVacancyDTOToVacancyDTOConverter
        implements GenericConverter<List<HeadHunterVacancyDTO>, List<VacancyDTO>> {

    private final ObjectMapper mapper;
    private final RequirementsToSkillsConverter requirementsToSkillsConverter;

    @Override
    public List<VacancyDTO> convert(List<HeadHunterVacancyDTO> vacancyDTOList) {
        return vacancyDTOList.stream()
                .map(hhVacancy -> VacancyDTO.builder()
                        .title(hhVacancy.getName())
                        .company(hhVacancy.getEmployer().getName())
                        .skills(requirementsToSkillsConverter.convert(hhVacancy.getSnippet().getRequirement()))
                        .salary(mapper.convertValue(hhVacancy.getSalary(), SalaryDTO.class))
                        .build())
                .collect(Collectors.toList());
    }
}
