package com.isakov.emcore.job.converters;

import com.isakov.emcore.job.dto.VacancyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VacancyDTOToSkillConverter implements GenericConverter<List<VacancyDTO>, Map<String, Integer>>{

    //TODO Переделать на SkillDTO + сортировка по количеству через SQL запрос. Можно добавить модель мепер для примера

    @Override
    public Map<String, Integer> convert(List<VacancyDTO> vacancyDTOList) {

        Map<String, Integer> map = new HashMap<>();

        for (VacancyDTO vacancy : vacancyDTOList) {
            Collection<String> skills = vacancy.getSkills();
            for (String skill : skills) {
                map.merge(skill, 1, Integer::sum);
            }
        }

        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap<String, Integer>::new));
    }
}

