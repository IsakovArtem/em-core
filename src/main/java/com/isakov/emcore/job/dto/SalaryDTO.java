package com.isakov.emcore.job.dto;

import lombok.Data;

@Data
public class SalaryDTO {
    private Integer from;
    private Integer to;
    private String currency;
    private Boolean gross;
}
