package com.dbms.model;

public class Billing {
    private final int billId;
    private final int patientId;
    private final int appointmentId;
    private final double totalAmount;
    private String paymentStatus;

    // Constructor for new bills (defaults to unpaid)
    public Billing(int billId, int patientId, int appointmentId, double totalAmount) {
        this.billId = billId;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.totalAmount = totalAmount;
        this.paymentStatus = "Unpaid";
    }

    // Constructor for bills fetched from DB
    public Billing(int billId, int patientId, int appointmentId, double totalAmount, String paymentStatus) {
        this.billId = billId;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    public int getBillId() {
        return billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void markAsPaid() {
        this.paymentStatus = "Paid";
    }

    public void displayBillInfo() {
        System.out.println("Bill ID: " + billId);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Payment Status: " + paymentStatus);
    }
}
