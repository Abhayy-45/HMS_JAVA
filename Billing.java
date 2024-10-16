public class Billing {
    private int billId;
    private int patientId;
    private int appointmentId;
    private double totalAmount;
    private String paymentStatus;
    public Billing(int billId, int patientId, int appointmentId, double totalAmount) {
        this.billId = billId;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.totalAmount = totalAmount;
        this.paymentStatus = "Unpaid"; 
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
