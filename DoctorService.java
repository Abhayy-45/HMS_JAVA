import java.util.ArrayList;
public class DoctorService {
    private ArrayList<Doctor> doctorList = new ArrayList<>();
    public void addDoctor(Doctor doctor) {
        doctorList.add(doctor);
        System.out.println("Doctor added successfully.");
    }
    public void deleteDoctor(int doctorId) {
        for (Doctor doctor : doctorList) {
            if (doctor.getDoctorId() == doctorId) {
                doctorList.remove(doctor);
                System.out.println("Doctor removed successfully.");
                return;
            }
        }
        System.out.println("Doctor not found.");
    }
    public void displayDoctors() {
        for (Doctor doctor : doctorList) {
            doctor.displayInfo(); 
        }
    }
}
