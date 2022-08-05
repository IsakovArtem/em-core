package com.isakov.emcore.job.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity(name = "vacancy")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String company;
    @Embedded
    private SalaryEntity salary;
    private Timestamp createDate;
    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinTable
            (
                    name = "VACANCY_SKILL",
                    joinColumns = @JoinColumn(name = "VACANCY_ID", referencedColumnName = "ID"),
                    inverseJoinColumns = @JoinColumn(name = "SKILL_ID", referencedColumnName = "ID")
            )
    private List<SkillEntity> skills;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyEntity that = (VacancyEntity) o;
        return Objects.equals(title, that.title)
                && Objects.equals(company, that.company)
                && Objects.equals(salary, that.salary)
                && Objects.equals(createDate, that.createDate)
                && Objects.equals(skills, that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, company, salary, createDate, skills);
    }
}
