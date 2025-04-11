package com.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dbms.service.AppointmentService;
import com.dbms.service.DoctorService;
import com.dbms.service.PatientService;
import com.dbms.ui.HospitalManagementUI;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hospitaldb";
        String user = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PatientService patientService = new PatientService(connection);
            DoctorService doctorService = new DoctorService(connection);
            AppointmentService appointmentService = new AppointmentService(connection);

            HospitalManagementUI ui = new HospitalManagementUI(patientService, doctorService, appointmentService);
            ui.start();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}
