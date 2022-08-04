package com.isakov.emcore.job.service.impl;

import com.isakov.emcore.job.dto.VacancyDTO;
import com.isakov.emcore.job.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VacancyServiceImpl implements VacancyService {

    @Override
    public List<VacancyDTO> findVacanciesBeVacancyName(String vacancyName) {
        return null;
    }

}
