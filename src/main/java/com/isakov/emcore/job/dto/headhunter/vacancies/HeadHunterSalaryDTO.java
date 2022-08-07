package com.isakov.emcore.job.dto.headhunter.vacancies;

import lombok.Data;

@Data
public class HeadHunterSalaryDTO {
    private Integer from;
    private Integer to;
    private String currency;
    private Boolean gross;
}
