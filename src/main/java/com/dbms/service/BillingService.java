package com.dbms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dbms.model.Billing;

public class BillingService {

    private final Connection conn;

    public BillingService(Connection conn) {
        this.conn = conn;
    }

    // Create a new bill for a patient
    public void createBill(int patientId, int appointmentId, double totalAmount) {
        String query = "INSERT INTO Billing (appointment_id, patient_id, total_amount, payment_status) VALUES (?, ?, ?, 'Unpaid')";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, appointmentId);
            pstmt.setInt(2, patientId);
            pstmt.setDouble(3, totalAmount);
            pstmt.executeUpdate();
            System.out.println("Bill created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating bill: " + e.getMessage());
        }
    }

    // Display billing history for a patient
    public void displayBillingHistory(int patientId) {
        String query = "SELECT * FROM Billing WHERE patient_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Billing History for Patient ID: " + patientId);
            while (rs.next()) {
                Billing bill = new Billing(
                    rs.getInt("bill_id"),
                    rs.getInt("patient_id"),
                    rs.getInt("appointment_id"),
                    rs.getDouble("total_amount"),
                    rs.getString("payment_status")
                );
                bill.displayBillInfo();
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Error displaying billing history: " + e.getMessage());
        }
    }

    // Mark a bill as paid
    public void markBillAsPaid(int billId) {
        String query = "UPDATE Billing SET payment_status = 'Paid' WHERE bill_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, billId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Bill marked as paid successfully.");
            } else {
                System.out.println("Bill not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error marking bill as paid: " + e.getMessage());
        }
    }

    // Display all outstanding (unpaid) bills
    public void displayOutstandingBills() {
        String query = "SELECT * FROM Billing WHERE payment_status = 'Unpaid'";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Outstanding Bills:");
            while (rs.next()) {
                Billing bill = new Billing(
                    rs.getInt("bill_id"),
                    rs.getInt("patient_id"),
                    rs.getInt("appointment_id"),
                    rs.getDouble("total_amount"),
                    rs.getString("payment_status")
                );
                bill.displayBillInfo();
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Error displaying outstanding bills: " + e.getMessage());
        }
    }

    // Update a bill's total amount and/or payment status
    public void updateBill(int billId, double newTotalAmount, String newPaymentStatus) {
        String query = "UPDATE Billing SET total_amount = ?, payment_status = ? WHERE bill_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, newTotalAmount);
            pstmt.setString(2, newPaymentStatus);
            pstmt.setInt(3, billId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Bill updated successfully.");
            } else {
                System.out.println("Bill not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating bill: " + e.getMessage());
        }
    }

    // Delete a bill
    public void deleteBill(int billId) {
        String query = "DELETE FROM Billing WHERE bill_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, billId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Bill deleted successfully.");
            } else {
                System.out.println("Bill not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting bill: " + e.getMessage());
        }
    }
}
