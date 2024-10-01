package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dao.StudentDao;
import org.example.dtos.StudentRequestDto;
import org.example.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentDao studentDao;

    public void createStudent(StudentRequestDto studentRequestDto){
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setSurname(studentRequestDto.getSurname());
        student.setPatronymic(studentRequestDto.getPatronymic());
        student.setBirthdate(studentRequestDto.getBirthdate());
        student.setGroup_name(studentRequestDto.getGroup_name());

        studentDao.addStudent(student);
    }

    public void deleteStudentById(Long id){
        studentDao.deleteStudent(id);
    }

    public List<Student> showStudent(){
        return studentDao.getAllStudent();
    }
}
