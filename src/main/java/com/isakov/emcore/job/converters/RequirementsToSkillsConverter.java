package com.isakov.emcore.job.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RequirementsToSkillsConverter implements GenericConverter <String, Set<String>> {

    private final Set<String> allSkills = Set.of("JVM", "Collections", "Stream", "Concurrency", "СУБД",
            "Postgres", "MySql", "Kotlin", "Java", "SQL", "Spring", "lombok", "mysql", "docker");

    public Set<String> convert(String requirements) {
        Set<String> result = new HashSet<>();
        String[] strings = requirements.split("[ ,.]+");
        for (String word : strings) {
            if (allSkills.stream().anyMatch(word::equalsIgnoreCase)) {
                result.add(word);
            }
        }
        return result;
    }
}
