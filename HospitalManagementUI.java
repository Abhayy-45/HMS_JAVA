import java.util.InputMismatchException;
import java.util.Scanner;
public class HospitalManagementUI {
    private DoctorService doctorService;
    private PatientService patientService;
    private AppointmentService appointmentService;
    private BillingService billingService = new BillingService(); 
    private Scanner scanner = new Scanner(System.in);
    public HospitalManagementUI(PatientService patientService, DoctorService doctorService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }
    public void start() {
        while (true) {
            System.out.println("\nHospital Management System");
            System.out.println("1. Add Doctor");
            System.out.println("2. Display Doctors");
            System.out.println("3. Delete Doctor");
            System.out.println("4. Add Patient");
            System.out.println("5. Display Patients");
            System.out.println("6. Delete Patient");
            System.out.println("7. Add Appointment");
            System.out.println("8. Display Appointments");
            System.out.println("9. Update Appointment Status");
            System.out.println("10. Display Confirmed Appointments");
            System.out.println("11. Display Cancelled Appointments");
            System.out.println("12. Display Completed Appointments");
            System.out.println("13. Create New Bill");
            System.out.println("14. Display Patient Billing History");
            System.out.println("15. Mark Bill as Paid");
            System.out.println("16. Display Outstanding Bills");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = getValidIntInput(); 
            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    displayDoctors();
                    break;
                case 3:
                    deleteDoctor(); 
                    break;
                case 4:
                    addPatient();
                    break;
                case 5:
                    displayPatients();
                    break;
                case 6:
                    deletePatient(); 
                    break;
                case 7:
                    addAppointment();
                    break;
                case 8:
                    displayAppointments();
                    break;
                case 9:
                    updateAppointmentStatus();
                    break;
                case 10:
                    displayAppointmentsByStatus("Confirmed");
                    break;
                case 11:
                    displayAppointmentsByStatus("Cancelled");
                    break;
                case 12:
                    displayAppointmentsByStatus("Completed");
                    break;
                case 13:
                    createNewBill();
                    break;
                case 14:
                    displayPatientBillingHistory(); 
                    break;
                case 15:
                    markBillAsPaid(); 
                    break;
                case 16:
                    displayOutstandingBills(); 
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    private int getValidIntInput() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); 
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); 
            }
        }
    }
    private void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = getValidIntInput();
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine(); 
        System.out.print("Enter Doctor Specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Enter Doctor Schedule (e.g., 10:00-12:00): ");
        String schedule = scanner.nextLine();
        Doctor doctor = new Doctor(id, name, specialization, schedule);
        doctorService.addDoctor(doctor);
    }
    private void displayDoctors() {
        doctorService.displayDoctors();
    }
    private void deleteDoctor() {
        System.out.print("Enter Doctor ID to delete: ");
        int id = getValidIntInput();
        doctorService.deleteDoctor(id); 
    }
    private void addPatient() {
        System.out.print("Enter Patient ID: ");
        int id = getValidIntInput();
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient Age: ");
        int age = getValidIntInput();
        System.out.print("Enter Patient Contact: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Patient Disease: ");
        String disease = scanner.nextLine();   
        Patient patient = new Patient(id, name, age, contact, disease);
        patientService.addPatient(patient);
    }
    private void displayPatients() {
        patientService.displayPatients();
    }
    private void deletePatient() {
        System.out.print("Enter Patient ID to delete: ");
        int id = getValidIntInput();
        patientService.deletePatient(id);
    }
    private void addAppointment() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = getValidIntInput();
        System.out.print("Enter Patient ID: ");
        int patientId = getValidIntInput();
        System.out.print("Enter Doctor ID: ");
        int doctorId = getValidIntInput();
        System.out.print("Enter Appointment Date (e.g., 2024-10-20): ");
        String date = scanner.nextLine();    
        Appointment appointment = new Appointment(appointmentId, patientId, doctorId, date);
        appointmentService.addAppointment(appointment);
    }
    private void displayAppointments() {
        appointmentService.displayAppointments();
    }
    private void updateAppointmentStatus() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = getValidIntInput();
        System.out.println("Choose New Status: ");
        System.out.println("1. Confirmed");
        System.out.println("2. Cancelled");
        System.out.println("3. Completed");
        int statusChoice = getValidIntInput();   
        String newStatus = "";
        switch (statusChoice) {
            case 1:
                newStatus = "Confirmed";
                break;
            case 2:
                newStatus = "Cancelled";
                break;
            case 3:
                newStatus = "Completed";
                break;
            default:
                System.out.println("Invalid status choice.");
                return;
        }  
        appointmentService.updateAppointmentStatus(appointmentId, newStatus);
    }
    private void displayAppointmentsByStatus(String status) {
        appointmentService.displayAppointmentsByStatus(status);
    }
    private void createNewBill() {
        System.out.print("Enter Patient ID: ");
        int patientId = getValidIntInput();
        System.out.print("Enter Appointment ID: ");
        int appointmentId = getValidIntInput();
        System.out.print("Enter Total Amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();

        billingService.createBill(patientId, appointmentId, totalAmount);
    }
    private void displayPatientBillingHistory() {
        System.out.print("Enter Patient ID: ");
        int patientId = getValidIntInput();
        billingService.displayBillingHistory(patientId);
    }
    private void markBillAsPaid() {
        System.out.print("Enter Bill ID: ");
        int billId = getValidIntInput();
        billingService.markBillAsPaid(billId);
    }
    private void displayOutstandingBills() {
        billingService.displayOutstandingBills();
    }
}
