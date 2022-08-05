package com.isakov.emcore.job.service.impl;

import com.isakov.emcore.job.converters.VacancyEntityToVacancyDTOConverter;
import com.isakov.emcore.job.dto.VacancyDTO;
import com.isakov.emcore.job.entity.VacancyEntity;
import com.isakov.emcore.job.repository.VacancyRepository;
import com.isakov.emcore.job.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final VacancyEntityToVacancyDTOConverter vacancyEntityToVacancyDTOConverter;

    @Override
    public List<VacancyDTO> findVacanciesByVacancyName(String vacancyName) {
        List<VacancyDTO> vacanciesDTO;
        List<VacancyEntity> vacanciesByTitle = vacancyRepository.findByTitle(vacancyName);
        //TODO В случаи отсутствия записей по вакансиям в БД стучаться в API HH
        vacanciesDTO = vacancyEntityToVacancyDTOConverter.convert(vacanciesByTitle);
        return vacanciesDTO;
    }
}
