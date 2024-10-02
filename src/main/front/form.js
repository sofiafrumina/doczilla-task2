$(document).ready(function () {
    const apiUrl = 'http://localhost:8080/students';

    // Функция для получения и отображения списка студентов
    function fetchStudents() {
        $.get(apiUrl, function (students) {
            const tableBody = $('#studentsTableBody');
            tableBody.empty();

            students.forEach(student => {
                const row = `
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.surname}</td>
                        <td>${student.patronymic}</td>
                        <td>${student.birthdate}</td>
                        <td>${student.group_name}</td>
                        <td><button class="delete-btn" data-id="${student.id}">Delete</button></td>
                    </tr>
                `;
                tableBody.append(row);
            });
        });
    }

    // Функция для создания студента
    $('#createStudentBtn').click(function () {
        const newStudent = {
            name: $('#name').val(),
            surname: $('#surname').val(),
            patronymic: $('#patronymic').val(),
            birthdate: $('#birthdate').val(),
            group_name: $('#group_name').val()
        };

        $.ajax({
            url: apiUrl,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(newStudent),
            success: function () {
                fetchStudents();  // Обновляем список студентов
                alert('Student created successfully');
            },
            error: function () {
                alert('Error creating student');
            }
        });
    });

    // Функция для удаления студента
    $(document).on('click', '.delete-btn', function () {
        const studentId = $(this).data('id');

        $.ajax({
            url: `${apiUrl}/${studentId}`,
            type: 'DELETE',
            success: function () {
                fetchStudents();  // Обновляем список студентов
                alert('Student deleted successfully');
            },
            error: function () {
                alert('Error deleting student');
            }
        });
    });

    // Инициализация: получение списка студентов при загрузке страницы
    fetchStudents();
});
