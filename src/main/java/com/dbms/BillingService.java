package com.dbms;

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

    public void createBill(int patientId, int appointmentId, double totalAmount) {
        String query = "INSERT INTO Billing (appointment_id, patient_id, total_amount, payment_status) VALUES (?, ?, ?, 'Unpaid')";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, appointmentId);
            pstmt.setInt(2, patientId);
            pstmt.setDouble(3, totalAmount);
            pstmt.executeUpdate();
            System.out.println("Bill created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }
}
