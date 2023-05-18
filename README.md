# Hospital Management System Project in Spring Boot

The Hospital Management System project is a RESTful API designed to manage various activities of a hospital, including patient management, doctor management, appointment management, and patient record management. The project aims to streamline hospital operations, reduce manual work, and improve service efficiency.

## Project Objective

The main objective of this project is to provide an online platform for managing hospital activities. The system allows doctors to check appointments, while receptionists can book appointments for new patients. By digitizing the workflow, the project reduces manual work, streamlines processes, and improves overall efficiency.

## Actors/Users

The application has three main actors (users):

1. Admin: Responsible for managing doctors and receptionists.
2. Doctor: Can view appointments, access patient lists, update appointment statuses, and change passwords.
3. Receptionist: Can add, edit, and view patients and appointments, update appointment statuses, and change passwords.

## Functional Requirements

### Admin

The admin has the following tasks and responsibilities:

- Add, delete, and update doctors
- View the list of doctors
- Add, delete, and update receptionists
- View the list of receptionists
- Add, delete, and update patients
- Add, delete, and update appointments
- Change password
- Update appointment status

### Doctor

The doctor can perform the following operations:

- View appointments
- View the patient list
- Update appointment status
- Change password

### Receptionist

The receptionist can perform the following operations:

- Add, edit, and view appointments
- Add, edit, and view patients
- Update appointment status
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


## APIs

All APIs require authentication. Admin has access to all APIs, while doctors and receptionists have access to specific APIs.

### Admin APIs

- `PATCH /hospital/user/update/password/{username}` - Update a user's password by username
- `GET /hospital/user/list` - Get a list of all users

### Doctor APIs

- `GET /hospital/doctor/appointment` - View appointments
- `GET /hospital/doctor/patient` - View the patient list
- `PATCH /hospital/doctor/appointment/{id}` - Update appointment status
- `PATCH /hospital/doctor/update/password/{username}` - Update the doctor's password

### Receptionist APIs

- `POST /hospital/receptionist/patient` - Add a new patient
- `PUT /hospital/receptionist/patient/{id}` - Update a patient by ID
- `GET /hospital/receptionist/patient` - Get a list of all patients
- `POST /hospital/receptionist/appointment` - Add a new appointment
- `PUT /hospital/receptionist/appointment/{id}` - Update an appointment by ID
- `PATCH /hospital/receptionist/update/password/{username}` - Update the receptionist's password

## DTO Classes

Both the Doctor and Receptionist entities have corresponding DTO (Data Transfer Object) classes that include two additional fields compared to the entity class: `password` and `role`.

Please refer to the source code for the DTO classes.

## Getting Started

To run the Hospital Management System project, follow these steps:

1. Clone the repository: `git clone [<repository-url>](https://github.com/adnan-laigroo/HospitalManagementSystem)`
2. Navigate to the project directory: `cd hospital-management-system`
3. Build the project: `./mvnw clean install`
4. Run the application: `./mvnw spring-boot:run`
5. Access the API endpoints using a tool like Postman or cURL.

## Note: Postman collection is also in the project folder.


