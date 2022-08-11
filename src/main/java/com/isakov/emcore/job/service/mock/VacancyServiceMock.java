package com.isakov.emcore.job.service.mock;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isakov.emcore.job.dto.VacancyDTO;
import com.isakov.emcore.job.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Profile("mock")
@Slf4j
@RequiredArgsConstructor
public class VacancyServiceMock implements VacancyService {

    private final ObjectMapper objectMapper;

    @Override
    public List<VacancyDTO> findVacanciesByVacancyName(String skill) {

        List<VacancyDTO> mockList;
        try {
            mockList = objectMapper.readValue(this.getClass()
                            .getClassLoader()
                            .getResourceAsStream("mock-responses/mock-vacancy-list.json"),
                    new TypeReference<>() {});
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return mockList;
    }
}