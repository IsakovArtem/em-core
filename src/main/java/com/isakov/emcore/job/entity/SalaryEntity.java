package com.isakov.emcore.job.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalaryEntity {
    @Column(name = "salary_from")
    private Integer from;
    @Column(name = "salary_to")
    private Integer to;
    private String currency;
    private boolean gross;
}
