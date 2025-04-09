package com.dbms.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dbms.model.Appointment;

public class AppointmentService {

    private final Connection connection; // Remove the redundant 'final' modifier

    public AppointmentService(Connection connection) {
        this.connection = connection;
    }

    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointment (patient_id, doctor_id, appointment_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, appointment.getPatientId());
            pstmt.setInt(2, appointment.getDoctorId());
            pstmt.setDate(3, Date.valueOf(appointment.getAppointmentDate()));
            pstmt.setString(4, appointment.getStatus());
            pstmt.executeUpdate();
            System.out.println("Appointment added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding appointment: " + e.getMessage());
        }
    }

    public void deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM Appointment WHERE appointment_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, appointmentId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment removed successfully.");
            } else {
                System.out.println("Appointment not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting appointment: " + e.getMessage());
        }
    }

    public void displayAppointments() {
        String sql = "SELECT * FROM Appointment";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean found = false;
            while (rs.next()) {
                int id = rs.getInt("appointment_id");
                int patientId = rs.getInt("patient_id");
                int doctorId = rs.getInt("doctor_id");
                Date date = rs.getDate("appointment_date");
                String status = rs.getString("status");

                Appointment appointment = new Appointment(id, patientId, doctorId, date.toLocalDate(), status);
                appointment.displayInfo();
                found = true;
            }

            if (!found) {
                System.out.println("No appointments available.");
            }
        } catch (SQLException e) {
            System.out.println("Error displaying appointments: " + e.getMessage());
        }
    }
}
