# StudentInformationManagement
![SISicon](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/2e19945e-9b2c-41df-9914-26bda0642dc1)
   ABSTRACT

The Student Information System (SIS) is a modern digital solution designed to improve student data management and communication within educational institutions. It centralizes student records, course details, and administrative information, offering a user-friendly interface for administrators, instructors, and students. Features include student record storage, course management, real-time updates, attendance tracking, and a communication hub. The SIS streamlines administrative processes, enhances communication, and empowers educators for better student success.
![BALA PT1 FINAL 1 - Word 12-10-2023 00_15_14](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/13a00c71-58cf-4704-af57-92c3d73514e5)
ARCHITECTHURE:

![ARCHITECTURE](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/3f7dabea-ae08-4e47-a274-713fa935ab76)

•	Client (Presentation Layer):

	The client layer represents the user interface or the part of the application that users interact with directly. In the context of your SIS project, this could include web pages, forms, and user interfaces through which students, instructors, and administrators interact with the system.

•	Local Host (Business Layer):

	The local host layer is often referred to as the business logic layer. It is responsible for handling the core functionality and business logic of the application. In your SIS project, this layer would contain the code that manages student records, course enrollment, attendance tracking, grading, and other essential operations. It processes requests from the client layer and interacts with the database layer for data retrieval and storage.

•	Database (Data Access Layer):

	The database layer serves as the data access layer and is responsible for managing the storage, retrieval, and manipulation of data. In your SIS project, you mentioned using the JDBC driver, which connects your application to the database (e.g., PostgreSQL). This layer handles database interactions, including executing SQL queries, updating records, and ensuring data consistency and integrity.

![CLOUD](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/01687ed3-798b-4fbf-8d0b-450737b7c5e3)

This three-tier architecture promotes separation of concerns, making the application more modular and maintainable. It allows for scalability and flexibility, as changes in one layer do not necessarily impact the others. Additionally, it enhances security by controlling access to the database and encapsulating business logic.
WORKING FLOW OF SIS MODEL:

713fa935ab76)![mvc](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/cbdb4524-1c61-44e3-a19b-36be686edf9d)

•	MODEL:

	The Model represents the application's data and business logic. It is responsible for managing data and the interactions with the database.
	In the context of working with a database, the Model typically includes classes and functions for data access, validation, and manipulation.
	It communicates with the database using technologies like JDBC (Java Database Connectivity) in Java-based applications.

•	VIEW:

	The View is responsible for presenting data to the user and handling user interfaces such as HTML templates, forms, and UI components.
	It does not directly communicate with the database but rather receives data from the Controller or the Model and renders it for the user to see.
	In web applications, HTML templates or web pages represent the View.

•	CONTROLLER:

	The Controller is the intermediary between the Model and the View. It handles user input and decides how to respond.
	When a user interacts with the application (e.g., submits a form), the Controller receives the input from the View.
	The Controller may validate the input, interact with the Model to retrieve or update data in the database, and then instruct the View to display the appropriate response.
	It is responsible for managing the flow of data and controlling the overall behavior of the application.

Here's how the MVC architecture works in a typical flow:

o	The user interacts with the application through the user interface (UI), typically by clicking buttons, filling out forms, or making requests.
o	The user's actions trigger requests to the Controller. The Controller receives these requests, interprets them, and decides how to handle them.
o	The Controller may interact with the Model to retrieve or update data. It invokes the appropriate methods on the Model to perform the necessary operations.
o	Once the Model has processed the data and performed the required operations, it provides the result back to the Controller.
o	The Controller selects the appropriate View to render the response. It passes the data received from the Model to the View for rendering.
o	The View takes the data and generates the user interface, which is then presented to the user.
o	The user can interact further with the application, and the cycle continues as needed.

ER-DIAGRAM:

•	The Student entity stores information about individual students, including their personal details and contact information.
•	The Course entity represents the courses offered by the institution, containing attributes like course name and credit hours.
•	The Instructor entity holds information about instructors who teach the courses, including their names and contact details.
•	The Enrollment entity captures the relationship between students and courses they are enrolled in, along with the enrollment date.

![sisfinal](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/35dd802e-a400-42a9-a08a-6cef84199d55)

USER INTERFACE

•	HTML has been used for developing the User Layout for the system
•	JavaScript has been used for creating all the validations and client side scripting functionality
•	CSS has been used for designing the web pages of the system

HARDWARE REQUIREMENT:

•	Processor        :  Intel Pentium 4 or more
•	Ram                 : 256 GB or more
•	Cache              : 1 MB
•	Hard Disk        :  10 GB or more

SOFTWARE INTERFACE:

•	Client on Internet: Web Browser, Operating System (any)
•	Web Server: Operating System (any), Apache 2
•	Database: Psql
•	Scripting Language: JSP, JavaScript, Servlet

COMMUNICATION PROTOCOL

Following protocols are required to be permitted on the server side HTTP incoming request.

FUNCTIONAL REQUIREMENTS

	The system runs of apache server so it is needed that server must have apache server version 2.0 available.
	We have used JSP for server side scripting so the current version of JSP must be available on the server
	PSQL database has been used for storing the data of the website
 	HTML has been used for creating the layout of the web application
	CSS has been used for creating the designing of the webpages
	JavaScript scripting language has been implemented on the system for performing all of the Client Side Server Validation.

CLASSES AND OBJECT:


•	Login.jsp -Used for performing HTML rendering for the login page.
•	Student.jsp -Used for rendering the student interface and displaying student and course details.
•	Instructor.jsp-Used for rendering the instructor interface and providing access to instructor-specific functionalities.
•	LoginDao.java-Handles database operations for user authentication, including verifying student and instructor login credentials.
•	StudentDao.java-Manages database operations related to student data, such as retrieving student details and course information.
•	Login.java-A Java servlet responsible for handling user login requests, verifying credentials, and redirecting users to the appropriate interface (student or instructor).
•	Logout.java-A Java servlet handling user logout requests, invalidating user sessions, and redirecting users to the login page.
•	StudentServlet.java-A Java servlet that handles AJAX requests for student details. It fetches student information from the database and returns it as JSON.
•	CourseServlet.java-A Java servlet that handles AJAX requests for course details. It retrieves course information from the database and returns it as JSON.

LOGIN PAGE
![LOGINPAGE](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/9df3bcf2-eb74-43bd-a08b-9bad7f59e7a7)
STUDENT – INTERFACE: 
![STUDENTDETAILSupdated](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/d524499e-2aec-492b-9a9c-26854e41148e)
COURSE – INTERFACE:
![COURSEPAGEupdated](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/0f27b529-6321-4fca-bdbf-b5e021802053)

![enroll_in](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/3ed0a908-d41e-4608-9707-e7f9bcd75134)

![enroll_in_sucessfull](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/aa68eeea-c886-464e-9905-54f4f7a73dc6)
INSTRUCTOR-DETAILS:
![instructorcoursedetails](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/918f659d-103b-4e11-a120-46530ecdf05e)
INSTRUCTOR-COURSE-DETAILS:
![instructordetails](https://github.com/ishhookayy/StudentInformationManagement/assets/138235393/f34d40e8-ff59-4dc1-bd9a-cd1342905b65)
