package com.dbms.service;

import java.sql.*;
import java.util.ArrayList;  // Import ArrayList
import com.dbms.model.Patient;

public class PatientService {

    private final Connection connection;  // Make connection final

    public PatientService(Connection connection) {
        this.connection = connection;
    }

    public void addPatient(Patient patient) {
        String sql = "INSERT INTO Patient (name, age, gender, contact, disease) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContact());
            stmt.setString(5, patient.getDisease());
            stmt.executeUpdate();
            System.out.println("Patient added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(int patientId) {
        String sql = "DELETE FROM Patient WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient removed successfully.");
            } else {
                System.out.println("Patient not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayPatients() {
        String sql = "SELECT * FROM Patient";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ArrayList<Patient> patientList = new ArrayList<>();  // Initialize ArrayList
            while (rs.next()) {
                int id = rs.getInt("patient_id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String contact = rs.getString("contact");
                String disease = rs.getString("disease");

                Patient patient = new Patient(id, name, age, gender, contact, disease);
                patientList.add(patient);
            }

            if (patientList.isEmpty()) {
                System.out.println("No patients available.");
            } else {
                for (Patient patient : patientList) {
                    patient.displayInfo();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
