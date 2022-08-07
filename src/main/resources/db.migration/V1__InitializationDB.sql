CREATE TABLE Vacancy
(
    id          int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title       varchar(250),
    company     varchar(250),
    salary_from numeric,
    salary_to   numeric,
    currency    varchar(250),
    gross       boolean,
    create_date timestamp
);

CREATE TABLE Skill
(
    id   int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(250)
);

CREATE TABLE Vacancy_Skill
(
    vacancy_id int REFERENCES Vacancy(id),
    skill_id   int REFERENCES Skill(id),
    PRIMARY KEY (vacancy_id, skill_id)
);







