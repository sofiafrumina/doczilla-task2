package org.example.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.time.LocalDate;
@RequiredArgsConstructor
@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthdate;
    private String group_name;

    public Student(Long id, String name, String surname, String patronymic, String group_name, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.group_name = group_name;

    }
}
