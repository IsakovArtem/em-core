package com.isakov.emcore.job.controllers;

import com.isakov.emcore.job.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class VacancyController {

    private final SkillService skillsService;

    @GetMapping("/skills/{vacancy_name}")
    public Map<String, Integer> execute(@PathVariable("vacancy_name") String vacancyName) {
        return skillsService.getSkills(vacancyName);
    }
}