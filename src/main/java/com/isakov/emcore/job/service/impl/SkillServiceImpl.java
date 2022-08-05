package com.isakov.emcore.job.service.impl;

import com.isakov.emcore.job.converters.VacancyDTOToSkillConverter;
import com.isakov.emcore.job.dto.VacancyDTO;
import com.isakov.emcore.job.service.SkillService;
import com.isakov.emcore.job.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final VacancyService vacancyService;
    private final VacancyDTOToSkillConverter vacancyDTOToSkillConverter;

    @Override
    public Map<String, Integer> getSkills(String vacancyName) {
        List<VacancyDTO> vacanciesByVacancyName = vacancyService.findVacanciesByVacancyName(vacancyName);
        return vacancyDTOToSkillConverter.convert(vacanciesByVacancyName);
    }
}
