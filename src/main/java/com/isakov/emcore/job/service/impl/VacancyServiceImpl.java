package com.isakov.emcore.job.service.impl;

import com.isakov.emcore.job.converters.HeadHunterVacancyDTOToVacancyDTOConverter;
import com.isakov.emcore.job.converters.VacancyEntityToVacancyDTOConverter;
import com.isakov.emcore.job.dto.VacancyDTO;
import com.isakov.emcore.job.dto.headhunter.vacancies.HeadHunterVacancyDTO;
import com.isakov.emcore.job.entity.VacancyEntity;
import com.isakov.emcore.job.repository.VacancyRepository;
import com.isakov.emcore.job.service.HeadHunterApiService;
import com.isakov.emcore.job.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("!mock")
@Slf4j
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final VacancyEntityToVacancyDTOConverter vacancyEntityToVacancyDTOConverter;
    private final HeadHunterApiService headHunterApiService;
    private final HeadHunterVacancyDTOToVacancyDTOConverter headHunterVacancyDTOToVacancyDTOConverter;

    @Override
    public List<VacancyDTO> findVacanciesByVacancyName(String vacancyName) {
        List<VacancyDTO> vacanciesDTO;
        List<VacancyEntity> vacanciesByTitle = vacancyRepository.findByTitle(vacancyName);

        if (vacanciesByTitle.isEmpty()) {
            vacanciesDTO = headHunterVacancyDTOToVacancyDTOConverter.convert(headHunterApiService.getVacancies(vacancyName));
        } else {
            vacanciesDTO = vacancyEntityToVacancyDTOConverter.convert(vacanciesByTitle);
        }
        return vacanciesDTO;
    }
}
