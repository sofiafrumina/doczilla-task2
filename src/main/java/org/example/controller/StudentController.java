package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dtos.StudentRequestDto;
import org.example.model.Student;
import org.example.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping
    public ResponseEntity<List<Student>> showStudent() {
        List<Student> students = studentService.showStudent();

        return ResponseEntity.ok(students);
    }
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody StudentRequestDto studentRequestDto){
        studentService.createStudent(studentRequestDto);

        return ResponseEntity.ok("Студент успешно создан");
    }
    @CrossOrigin(origins = "http://localhost:63342")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);

        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
