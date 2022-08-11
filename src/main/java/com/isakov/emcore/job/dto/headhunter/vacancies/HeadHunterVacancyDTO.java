package com.isakov.emcore.job.dto.headhunter.vacancies;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
//_example @JsonIgnoreProperties(ignoreUnknown = true)
public class HeadHunterVacancyDTO {
    private Long id;
    private String name;
    private String description;
    private HeadHunterSalaryDTO salary;
    private HeadHunterEmployerDTO employer;
    private SnippetDTO snippet;
    private List<String> keySkills = new ArrayList<>();

    @JsonSetter("branded_description")
    private void setBrandedDescription(String brandedDescription) {
        this.description += brandedDescription;
    }

    @JsonSetter("key_skills")
    private void addSkill(HeadHunterSkillDTO[] skills) {
        for (HeadHunterSkillDTO skill : skills) {
            this.keySkills.add(skill.getName());
        }
    }
}
