package org.example.dtos;
import lombok.Data;
import java.sql.Date;
import java.time.LocalDate;

@Data
public class StudentRequestDto {
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthdate;
    private String group_name;
}

