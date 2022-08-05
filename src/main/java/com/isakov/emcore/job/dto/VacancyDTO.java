package com.isakov.emcore.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDTO {
    private String company;
    /** пример от 510 000 до 572 000 руб. до вычета налогов */
    private SalaryDTO salary;
    private String title;
    /** пример "Java" "Spring Framework" */
    private Set<String> skills;
}
