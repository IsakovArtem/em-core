package com.isakov.emcore.job.service;

import com.isakov.emcore.job.dto.VacancyDTO;
import java.util.List;

public interface VacancyService {

    List<VacancyDTO> findVacanciesBeVacancyName(String vacancyName);

}

