<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.login.dao.InstructorDao" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Instructor Interface</title>
    <link rel="icon" type="image/png" href="images/SISicon.png">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Instructor Interface</h1>
        <div class="options">
            <button onclick="showInstructorDetails()">Instructor Details</button>
            <button onclick="showCourseDetails()">Course Details</button>
        </div>
        <div class="content hidden" id="content">
            <div id="instructorDetails" class="hidden">
                <h2>Instructor Details</h2>
                <!-- Display instructor details here -->
                <div id="instructorDetailsOutput">
                    <p>Instructor ID: <span id="instructorId"></span></p>
                    <p>First Name: <span id="firstName"></span></p>
                    <p>Last Name: <span id="lastName"></span></p>
                    <p>Salary: <span id="salary"></span></p>
                    <!-- Add other instructor details here -->
                </div>
            </div>
            <div id="courseDetails" class="hidden">
                <h2>Course Details</h2>
                <!-- Display course details here -->
                <div id="courseDetailsOutput">
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
    function showInstructorDetails() {
        // Make an AJAX request to fetch instructor details
        fetch('/StudentInformationManagement/InstructorDetailsServlet')
            .then(response => response.json())
            .then(data => {
                // Display instructor details in the HTML
                const instructorId = document.getElementById('instructorId');
                const firstName = document.getElementById('firstName');
                const lastName = document.getElementById('lastName');
                const salary = document.getElementById('salary');

                instructorId.innerText = data[0].instructorId;
                firstName.innerText = data[0].firstName;
                lastName.innerText = data[0].lastName;
                salary.innerText = data[0].salary;

                // Show the instructor details section
                document.getElementById('instructorDetails').classList.remove('hidden');
                document.getElementById('courseDetails').classList.add('hidden');
            })
            .catch(error => {
                console.error('Error fetching instructor details:', error);
            });
    }

    function showCourseDetails() {
        // Make an AJAX request to fetch course details for the instructor
        fetch('/StudentInformationManagement/InstructorCoursesServlet')
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
                    tableHeader.innerHTML = '<th>Course ID</th><th>Course Name</th><th>Credit Hours</th>';
                    table.appendChild(tableHeader);

                    // Create table rows for each course
                    data.forEach(course => {
                        const courseRow = document.createElement('tr');
                        // Create table cells for each data field
                        const courseIdCell = document.createElement('td');
                        courseIdCell.textContent = course.courseId;
                        const courseNameCell = document.createElement('td');
                        courseNameCell.textContent = course.courseName;
                        const creditHoursCell = document.createElement('td');
                        creditHoursCell.textContent = course.creditHours;

                        // Append cells to the row
                        courseRow.appendChild(courseIdCell);
                        courseRow.appendChild(courseNameCell);
                        courseRow.appendChild(creditHoursCell);

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
                document.getElementById('instructorDetails').classList.add('hidden');
            })
            .catch(error => {
                console.error('Error fetching course details:', error);
            });
    }
    </script>
</body>
</html>
