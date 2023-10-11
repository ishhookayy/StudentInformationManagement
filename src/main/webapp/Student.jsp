<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.login.dao.StudentDao" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Interface</title>
    <link rel="icon" type="image/png" href="images/SISicon.png">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Student Interface</h1>
        <div class="options">
            <button onclick="showStudentDetails()">Student Details</button>
            <button onclick="showCourseDetails()">Course Details</button>
            <button onclick="showCourseEnrollment()">Enroll in a Course</button>
        </div>
        <div class="content hidden" id="content">
            <div id="studentDetails" class="hidden">
                <h2>Student Details</h2>
                <!-- Display student details here -->
                <div id="studentDetailsOutput">
                    <p>Student ID: <span id="studentId"></span></p>
                    <p>First Name: <span id="firstName"></span></p>
                    <p>Last Name: <span id="lastName"></span></p>
                    <p>Date of Birth: <span id="dateOfBirth"></span></p>
                    <p>City: <span id="city"></span></p>
                </div>
            </div>
            <div id="courseDetails" class="hidden">
                <h2>Course Details</h2>
                <!-- Display course details here -->
                <div id="courseDetailsOutput">
                    <!-- Courses will be displayed here -->
                </div>
            </div>
            <div id="courseEnrollment" class="hidden">
                <h2>Enroll in a Course</h2>
                <div>
                    <select id="courseDropdown"></select>
                    <button onclick="enrollInCourse()">Enroll</button>
                </div>
            </div>
        </div>
    </div>
    <%
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    if (session.getAttribute("username") == null) {
        response.sendRedirect("Login.jsp");
    }
    %>
    <form action="Logout">
        <input type="submit" value="Logout">
    </form>
    <script>
        function showStudentDetails() {
            // Make an AJAX request to fetch student details
            fetch('/StudentInformationManagement/StudentDetailsServlet') // Use a relative URL
                .then(response => response.json())
                .then(data => {
                    // Display student details in the HTML
                    const studentId = document.getElementById('studentId');
                    const firstName = document.getElementById('firstName');
                    const lastName = document.getElementById('lastName');
                    const dateOfBirth = document.getElementById('dateOfBirth');
                    const city = document.getElementById('city');

                    studentId.innerText = data[0].studentId;
                    firstName.innerText = data[0].firstName;
                    lastName.innerText = data[0].lastName;
                    dateOfBirth.innerText = data[0].dateOfBirth;
                    city.innerText = data[0].city;

                    // Show the student details section
                    document.getElementById('studentDetails').classList.remove('hidden');
                    document.getElementById('courseDetails').classList.add('hidden');
                    document.getElementById('courseEnrollment').classList.add('hidden');
                })
                .catch(error => {
                    console.error('Error fetching student details:', error);
                });
        }

        function showCourseDetails() {
            // Make an AJAX request to fetch course details
            fetch('/StudentInformationManagement/CourseServlet')
                .then(response => response.json())
                .then(data => {
                    // Display course details in a table
                    const courseDetailsOutput = document.getElementById('courseDetailsOutput');
                    courseDetailsOutput.innerHTML = ''; // Clear previous content

                    if (data.length > 0) {
                        // Create a table element
                        const table = document.createElement('table');
                        table.border = '1';

                        // Create table headers
                        const tableHeader = document.createElement('tr');
                        tableHeader.innerHTML = '<th>Course ID</th><th>Course Name</th><th>Enrollment Date</th>';
                        table.appendChild(tableHeader);

                        // Create table rows for each course
                        data.forEach(course => {
                            const courseRow = document.createElement('tr');
                            // Create table cells for each data field
                            const courseIdCell = document.createElement('td');
                            courseIdCell.textContent = course.courseId;
                            const courseNameCell = document.createElement('td');
                            courseNameCell.textContent = course.courseName;
                            const enrollmentDateCell = document.createElement('td');
                            enrollmentDateCell.textContent = course.enrollmentDate;

                            // Append cells to the row
                            courseRow.appendChild(courseIdCell);
                            courseRow.appendChild(courseNameCell);
                            courseRow.appendChild(enrollmentDateCell);

                            // Append the row to the table
                            table.appendChild(courseRow);
                        });

                        // Append the table to the output div
                        courseDetailsOutput.appendChild(table);
                    } else {
                        // Handle the case where there are no courses to display
                        courseDetailsOutput.textContent = 'No courses found.';
                    }

                    // Show the course details section
                    document.getElementById('courseDetails').classList.remove('hidden');
                    document.getElementById('studentDetails').classList.add('hidden');
                    document.getElementById('courseEnrollment').classList.add('hidden');
                })
                .catch(error => {
                    console.error('Error fetching course details:', error);
                });
        }

        function showCourseEnrollment() {
            document.getElementById('studentDetails').classList.add('hidden');
            document.getElementById('courseDetails').classList.add('hidden');
            document.getElementById('courseEnrollment').classList.remove('hidden');

            loadAvailableCourses(); // Load available courses when switching to course enrollment
        }

        function loadAvailableCourses() {
            const courseDropdown = document.getElementById('courseDropdown');

            // Make an AJAX request to fetch available courses
            fetch('/StudentInformationManagement/AvailableCoursesServlet')
                .then(response => response.json())
                .then(data => {
                    // Clear existing options
                    courseDropdown.innerHTML = '';

                    if (data.length > 0) {
                        // Populate dropdown options
                        data.forEach(course => {
                            const option = document.createElement('option');
                            option.value = course.courseId;
                            option.textContent = course.courseName;
                            courseDropdown.appendChild(option);
                        });
                    } else {
                        // Handle the case where there are no available courses
                        courseDropdown.innerHTML = '<option value="">No courses available</option>';
                    }
                })
                .catch(error => {
                    console.error('Error fetching available courses:', error);
                });
        }

        // Function to enroll in a course
        function enrollInCourse() {
            const courseDropdown = document.getElementById('courseDropdown');
            const selectedCourseId = courseDropdown.value;

            if (selectedCourseId) {
                // Make an AJAX request to enroll in the selected course
                fetch('/StudentInformationManagement/EnrollCourseServlet?courseId=' + selectedCourseId)
                    .then(response => response.json())
                    .then(data => {
                        if (data.success) {
                            // Successful enrollment, you can update the UI as needed
                            alert('Enrollment successful.');
                        } else {
                            // Enrollment failed, possibly due to duplicate enrollment
                            alert('Enrollment failed. You are already enrolled in this course.');
                        }
                    })
                    .catch(error => {
                        console.error('Error enrolling in the course:', error);
                    });
            }
        }
    </script>
</body>
</html>
