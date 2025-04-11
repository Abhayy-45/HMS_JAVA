package com.dbms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dbms.model.Doctor;

public class DoctorService {

    private final Connection connection;

    public DoctorService(Connection connection) {
        this.connection = connection;
    }

    public void addDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctor (name, specialization, availability, contact_number) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.setString(3, doctor.getAvailability());
            stmt.setString(4, doctor.getContactNumber());
            stmt.executeUpdate();
            System.out.println("Doctor added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
    }

    public void deleteDoctor(int doctorId) {
        String sql = "DELETE FROM Doctor WHERE doctor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Doctor removed successfully.");
            } else {
                System.out.println("Doctor not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting doctor: " + e.getMessage());
        }
    }

    public void displayDoctors() {
        String sql = "SELECT * FROM Doctor";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ArrayList<Doctor> doctorList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("doctor_id");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                String availability = rs.getString("availability");
                String contactNumber = rs.getString("contact_number");

                Doctor doctor = new Doctor(id, name, specialization, availability, contactNumber);
                doctorList.add(doctor);
            }

            if (doctorList.isEmpty()) {
                System.out.println("No doctors available.");
            } else {
                for (Doctor doctor : doctorList) {
                    doctor.displayInfo();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error displaying doctors: " + e.getMessage());
        }
    }
}
