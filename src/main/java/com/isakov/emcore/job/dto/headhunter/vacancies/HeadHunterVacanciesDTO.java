package com.isakov.emcore.job.dto.headhunter.vacancies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
//_example @JsonIgnoreProperties(ignoreUnknown = true)
public class HeadHunterVacanciesDTO {
    private List<HeadHunterVacancyDTO> items;
    private Integer found;
    private Integer pages;
    private Integer page;
    private Integer per_page;
}
