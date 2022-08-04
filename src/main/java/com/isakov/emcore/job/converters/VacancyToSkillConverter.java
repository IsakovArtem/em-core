package com.isakov.emcore.job.converters;

import com.isakov.emcore.job.dto.VacancyDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class VacancyToSkillConverter implements GenericConverter<List<VacancyDTO>, Map<String, Integer>>{

    @Override
    public Map<String, Integer> convert(List<VacancyDTO> vacancyDTOList) {
        return null;
    }
}
