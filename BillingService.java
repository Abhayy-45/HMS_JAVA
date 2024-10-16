import java.util.ArrayList;
public class BillingService {
    private ArrayList<Billing> billingList = new ArrayList<>();
    private int billCounter = 1; 
    public void createBill(int patientId, int appointmentId, double totalAmount) {
        Billing bill = new Billing(billCounter++, patientId, appointmentId, totalAmount);
        billingList.add(bill);
        System.out.println("Bill created successfully.");
    }
    public void displayBillingHistory(int patientId) {
        System.out.println("Billing History for Patient ID: " + patientId);
        for (Billing bill : billingList) {
            if (bill.getPatientId() == patientId) {
                bill.displayBillInfo();
                System.out.println();
            }
        }
    }
    public void markBillAsPaid(int billId) {
        for (Billing bill : billingList) {
            if (bill.getBillId() == billId) {
                bill.markAsPaid();
                System.out.println("Bill marked as paid successfully.");
                return;
            }
        }
        System.out.println("Bill not found.");
    }
    public void displayOutstandingBills() {
        System.out.println("Outstanding Bills:");
        for (Billing bill : billingList) {
            if (bill.getPaymentStatus().equals("Unpaid")) {
                bill.displayBillInfo();
                System.out.println();
            }
        }
    }
}
