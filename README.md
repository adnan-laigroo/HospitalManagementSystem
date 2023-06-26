# Hospital Management System Project in Spring Boot

The Hospital Management System project is a RESTful API designed to manage various activities of a hospital, including patient management, doctor management, appointment management, patient record management, prescription management, medicine management, and medical test management. The project aims to streamline hospital operations, reduce manual work, and improve service efficiency.

## Project Objective

The main objective of this project is to provide an online platform for managing hospital activities. The system allows doctors to check appointments, view patient records, prescribe medicines, and order medical tests. Receptionists can book appointments for new patients, manage patient records, and handle prescription and medical test requests. Admins have access to all functionalities and can manage doctors, receptionists, medicines, and medical tests. By digitizing the workflow and integrating various functionalities, the project reduces manual work, streamlines processes, and improves overall efficiency.

## Actors/Users

The application has four main actors (users):

1. Admin: Responsible for managing doctors, receptionists, medicines, and medical tests.
2. Doctor: Can view appointments, access patient records, prescribe medicines, and order medical tests.
3. Receptionist: Can add, edit, and view patients, appointments, prescriptions, and medical test requests.
4. Pharmacist: Can manage medicines, including adding, updating, and deleting medicines from the inventory.

## Functional Requirements

### Admin

The admin has the following tasks and responsibilities:

- Add, delete, and update doctors
- View the list of doctors
- Add, delete, and update receptionists
- View the list of receptionists
- Add, delete, and update medicines
- View the list of medicines
- Add, delete, and update medical tests
- View the list of medical tests
- Change password

### Doctor

The doctor can perform the following operations:

- View appointments
- View patient records
- Prescribe medicines for patients
- Order medical tests for patients
- Change password

### Receptionist

The receptionist can perform the following operations:

- Add, edit, and view patients
- Add, edit, and view appointments
- Add, edit, and view prescriptions
- Add, edit, and view medical test requests
- Change password

### Pharmacist

The pharmacist can perform the following operations:

- Add, edit, and view medicines
- Change password

## Data Model and Backend Validation
### Doctor

The `Doctor` class has the following fields and backend validations:

- `firstname` (String): Only alphabets are allowed.
- `lastname` (String): Only alphabets are allowed.
- `emailid` (String, PK): Should be a valid email address. It serves as the doctor's username.
- `phoneno` (String): Only digits are allowed, and it must have at least 10 characters.
- `speciality` (String): Represents the doctor's specialization.

### Receptionist

The `Receptionist` class has the following fields and backend validations:

- `firstname` (String): Only alphabets are allowed.
- `lastname` (String): Only alphabets are allowed.
- `emailid` (String, PK): Should be a valid email address. It serves as the receptionist's username.
- `phoneno` (String): Only digits are allowed, and it must have at least 10 characters.

### Patient

The `Patient` class has the following fields and backend validations:

- `patid` (int, PK): Automatically generated ID with the format "P" followed by 5 digits.
- `firstname` (String): Only alphabets are allowed.
- `lastname` (String): Only alphabets are allowed.
- `address` (Address): Represents the patient's address.
- `phoneno` (String): Only digits are allowed, and it must have at least 10 characters.
- `symptom` (String): Represents the patient's symptom.

### Appointment

The `Appointment` class has the following fields and backend validations:

- `apid` (int, PK): Automatically generated ID with the format "A" followed by 6 digits.
- `patid` (int): Must exist in the `Patient` entity.
- `bloodGroup` (String): Should be a valid blood group value.
- `docid` (int): Must exist in the `Doctor` entity. The `docid` should be according to the patient's symptom.
- `appointmentDate` (LocalDate): Represents the appointment date and should be automatically entered as the current date.
- `appointmentTime` (String): Represents the appointment time in the format "hh:mm:ss".
- `appointmentStatus` (String): Represents the appointment status. It should be initially set to "pending" and can be updated by doctors, admin, or receptionists after the patient and doctor meeting.

#### Save Appointment Function

The `saveAppointment` function in the `AppointmentServiceImplementation` class has been enhanced to check for appointment time conflicts and assign the appointment to the doctor with the least pending appointments.
### Prescription

The `Prescription` class has the following fields:

- `apId` (String, PK): Represents the appointment ID, which is also used as the prescription ID.
- `patientName` (String): Stores the patient's name.
- `doctorName` (String): Stores the doctor's name.
- `symptom` (String): Represents the patient's symptom.
- `diagnosis` (String): Represents the doctor's diagnosis.
- `prescribedMedicines` (List of `PrescribedMedicine`): Contains the list of prescribed medicines.
- `prescribedTests` (List of `PrescribedTest`): Contains the list of prescribed medical tests.
- `notes` (String): Additional notes or instructions.

### PrescribedMedicine

The `PrescribedMedicine` class has the following fields:

- `medicineName` (String): Stores the name of the prescribed medicine.
- `dosage` (String): Represents the dosage instructions for the medicine.
- `frequency` (String): Indicates the frequency of medication.

### PrescribedTest

The `PrescribedTest` class has the following fields:

- `testName` (String): Stores the name of the prescribed medical test.
- `testResult` (String): Represents the test result.

### Medicine

The `Medicine` class has the following fields:

- `medicineName` (String, PK): Represents the name of the medicine.
- `manufacturer` (String): Stores the name of the medicine manufacturer.
- `description` (String): Provides a description of the medicine.
- `price` (double): Represents the price of the medicine.
- `quantity` (int): Indicates the quantity of the medicine in stock.

### MedicalTest

The `MedicalTest` class has the following fields:

- `testName` (String, PK): Represents the name of the medical test.
- `testDescription` (String): Provides a description of the medical test.
- `testPrice` (double): Represents the price of the medical test.
- `testType` (String): Indicates the type of the medical test.

## Technologies Used

The project is implemented using the following technologies:

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL (Database)
- Maven (Dependency Management)
- Postman (API Testing)
  
 #### Features

1. Appointment Time Conflict Check: Before saving the appointment, the function now checks if the appointment time conflicts with existing appointments for the doctor with the least pending appointments. It retrieves the list of appointments for the doctor and compares the appointment date and time with the new appointment. If a conflict is found, the function removes the doctor from the list and gets the next available doctor.

2. Removing Conflicting Doctor: When a conflict is detected, the doctor with the least pending appointments is removed from the `doctorList` using the `removeIf` method. This ensures that the same doctor is not assigned again when there is a conflict.

#### Usage

To use the updated `saveAppointment` function, follow these steps:

1. Ensure that you have the Hospital Management System project set up and running.

2. Make a POST request to the `/hospital/appointment` endpoint with the appointment details. The system will automatically check for appointment time conflicts and assign the appointment to the doctor with the least pending appointments.

3. If a conflict is found, the appointment will be assigned to the next available doctor.

4. The appointment will be saved with the updated doctor assigned and other relevant details.

#### Additional Notes

- The `saveAppointment` function now performs more efficient and intelligent assignment of appointments, taking into account the least pending appointments and time conflicts.

- This enhancement helps to optimize the distribution of appointments and improves the overall efficiency of the hospital management system.

- The code has been thoroughly tested to ensure proper functionality and handling of appointment time conflicts.

- Feel free to explore other endpoints and functionalities of the Hospital Management System project for managing doctors, patients, and appointments.


## Getting Started

To get started with the project, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse).
3. Configure the MySQL database connection in `application.properties`.
4. Build the project using Maven.
5. Run the application.
6. Use Postman or any other API testing tool to test the endpoints.


## Note: Postman collection is also in the project folder.

## API Endpoints

The project exposes the following API endpoints:

- `/api/doctors` (GET, POST, PUT, DELETE): Used for managing doctors.
- `/api/receptionists` (GET, POST, PUT, DELETE): Used for managing receptionists.
- `/api/medicines` (GET, POST, PUT, DELETE): Used for managing medicines.
- `/api/medicaltests` (GET, POST, PUT, DELETE): Used for managing medical tests.
- `/api/patients` (GET, POST, PUT, DELETE): Used for managing patients.
- `/api/appointments` (GET, POST, PUT, DELETE): Used for managing appointments.
- `/api/prescriptions` (GET, POST, PUT, DELETE): Used for managing prescriptions.
- `/api/medicinetests` (GET, POST, PUT, DELETE): Used for managing prescribed medicines and tests within a prescription.

Note: Replace `api` with the appropriate base URL or endpoint base path.

## Project Structure

The project follows a standard Spring Boot application structure:

- `src/main/java/com/example/hospital`: Contains the main Java classes and packages.
  - `config`: Contains the configuration classes for Spring Boot and database.
  - `controller`: Contains the RESTful API controllers.
  - `dto`: Contains the Data Transfer Objects used for request/response mapping.
  - `exception`: Contains the custom exception classes.
  - `model`: Contains the entity classes representing the data model.
  - `repository`: Contains the JPA repositories for database operations.
  - `service`: Contains the service classes for business logic.
  - `HospitalManagementSystemApplication.java`: The entry point of the Spring Boot application.
- `src/main/resources`: Contains the application properties and database schema script.
- `src/test/java/com/example/hospital`: Contains the test classes.

## Conclusion

The Hospital Management System project provides a comprehensive solution for managing various activities within a hospital. It utilizes Spring Boot and other technologies to build a scalable and efficient backend system. The project can be further enhanced by adding more features, such as billing management, user authentication, and reporting functionalities.

For any questions or feedback, please feel free to reach out.
