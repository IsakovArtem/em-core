package com.isakov.emcore.job.service;

import com.isakov.emcore.job.dto.headhunter.vacancies.HeadHunterVacancyDTO;

import java.util.List;

public interface HeadHunterApiService {

    List<HeadHunterVacancyDTO> getVacancies(String queryParam);
    List<HeadHunterVacancyDTO> getVacancies(String queryParam, String page, String perPage);
    HeadHunterVacancyDTO getVacancy(long id);
}
