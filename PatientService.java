import java.util.ArrayList;
public class PatientService {
    private ArrayList<Patient> patientList = new ArrayList<>();
    public void addPatient(Patient patient) {
        patientList.add(patient);
        System.out.println("Patient added successfully.");
    }
    public void deletePatient(int patientId) {
        for (Patient patient : patientList) {
            if (patient.getPatientId() == patientId) {
                patientList.remove(patient);
                System.out.println("Patient removed successfully.");
                return;
            }
        }
        System.out.println("Patient not found.");
    }
    public void displayPatients() {
        if (patientList.isEmpty()) {
            System.out.println("No patients available.");
            return;
        }
        for (Patient patient : patientList) {
            patient.displayInfo();
        }
    }
}
