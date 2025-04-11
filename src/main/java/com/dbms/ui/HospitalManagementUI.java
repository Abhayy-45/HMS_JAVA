package com.dbms.ui;

import java.time.LocalDate;
import java.util.Scanner;

import com.dbms.model.Appointment;
import com.dbms.model.Doctor;
import com.dbms.model.Patient;
import com.dbms.service.AppointmentService;
import com.dbms.service.DoctorService;
import com.dbms.service.PatientService;

public class HospitalManagementUI {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    public HospitalManagementUI(PatientService patientService, DoctorService doctorService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Display Patients");
            System.out.println("3. Delete Patient");
            System.out.println("4. Add Doctor");
            System.out.println("5. Display Doctors");
            System.out.println("6. Delete Doctor");
            System.out.println("7. Add Appointment");
            System.out.println("8. Display Appointments");
            System.out.println("9. Delete Appointment");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Patient Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int page = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter Gender: ");
                    String pgender = sc.nextLine();
                    System.out.print("Enter Contact: ");
                    String pcontact = sc.nextLine();
                    System.out.print("Enter Disease: ");
                    String pdisease = sc.nextLine();
                    Patient patient = new Patient(0, pname, page, pgender, pcontact, pdisease);
                    patientService.addPatient(patient);
                }

                case 2 -> patientService.displayPatients();

                case 3 -> {
                    System.out.print("Enter Patient ID to delete: ");
                    int dpId = sc.nextInt();
                    patientService.deletePatient(dpId);
                }

                case 4 -> {
                    System.out.print("Enter Doctor Name: ");
                    String dname = sc.nextLine();
                    System.out.print("Enter Specialty: ");
                    String dspecialty = sc.nextLine();
                    System.out.print("Enter Availability (e.g., Mon-Fri 9AM-5PM): ");
                    String davailability = sc.nextLine(); // <-- Newly added input
                    System.out.print("Enter Contact: ");
                    String dcontact = sc.nextLine();
                    Doctor doctor = new Doctor(0, dname, dspecialty, davailability, dcontact); // <-- Updated constructor
                    doctorService.addDoctor(doctor);
                }

                case 5 -> doctorService.displayDoctors();

                case 6 -> {
                    System.out.print("Enter Doctor ID to delete: ");
                    int ddId = sc.nextInt();
                    doctorService.deleteDoctor(ddId);
                }

                case 7 -> {
                    System.out.print("Enter Appointment Doctor ID: ");
                    int adid = sc.nextInt();
                    System.out.print("Enter Appointment Patient ID: ");
                    int apid = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter Date (yyyy-mm-dd): ");
                    String dateStr = sc.nextLine();
                    LocalDate appointmentDate = LocalDate.parse(dateStr);
                    System.out.print("Enter Appointment Status: ");
                    String status = sc.nextLine();
                    Appointment appointment = new Appointment(adid, apid, appointmentDate, status);
                    appointmentService.addAppointment(appointment);
                }

                case 8 -> appointmentService.displayAppointments();

                case 9 -> {
                    System.out.print("Enter Appointment ID to delete: ");
                    int daid = sc.nextInt();
                    appointmentService.deleteAppointment(daid);
                }

                case 10 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }

                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
