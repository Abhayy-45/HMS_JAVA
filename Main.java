public class Main {
    public static void main(String[] args) {
        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        AppointmentService appointmentService = new AppointmentService();
        HospitalManagementUI ui = new HospitalManagementUI(patientService, doctorService, appointmentService);
        ui.start();  
    }
}
