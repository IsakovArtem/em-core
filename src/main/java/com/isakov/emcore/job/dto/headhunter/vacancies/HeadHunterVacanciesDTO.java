package com.isakov.emcore.job.dto.headhunter.vacancies;

import lombok.Data;

import java.util.List;

@Data
public class HeadHunterVacanciesDTO {
    private List<HeadHunterVacancyDTO> items;
    private Integer found;
    private Integer pages;
    private Integer page;
    private Integer per_page;
}
