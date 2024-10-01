package org.example.dao;
import org.example.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class StudentDao {
    private final String url = "jdbc:postgresql://localhost:5432/student_db";
    private final String name = "postgres";
    private final String password = "password";

    // Получение всех студентов
    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, name, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) {

            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("patronymic"),
                        resultSet.getString("group_name"),
                        resultSet.getDate("birthdate").toLocalDate()
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Добавление студента
    public void addStudent(Student student) {
        try (Connection connection = DriverManager.getConnection(url, name, password);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (name, surname, patronymic, birthdate, group_name) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getPatronymic());
            preparedStatement.setDate(4, Date.valueOf(student.getBirthdate()));
            preparedStatement.setString(5, student.getGroup_name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление студента по ID
    public void deleteStudent(Long id) {
        try (Connection connection = DriverManager.getConnection(url, name, password);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students WHERE id = ?")) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}