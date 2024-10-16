import java.util.ArrayList;
import java.util.Iterator;
public class AppointmentService {
    private ArrayList<Appointment> appointmentList = new ArrayList<>();
    public void addAppointment(Appointment appointment) {
        appointmentList.add(appointment);
        System.out.println("Appointment added successfully.");
    }
    public void deleteAppointment(int appointmentId) {
        Iterator<Appointment> iterator = appointmentList.iterator();
        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getAppointmentId() == appointmentId) {
                iterator.remove(); // Safe removal
                System.out.println("Appointment removed successfully.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }
    public void updateAppointmentStatus(int appointmentId, String newStatus) {
        if (newStatus == null || newStatus.isEmpty()) {
            System.out.println("Invalid status. Appointment status not updated.");
            return;
        }
        for (Appointment appointment : appointmentList) {
            if (appointment.getAppointmentId() == appointmentId) {
                appointment.setStatus(newStatus);
                System.out.println("Appointment status updated to: " + newStatus);
                return;
            }
        }
        System.out.println("Appointment not found.");
    }
    public void displayAppointments() {
        for (Appointment appointment : appointmentList) {
            appointment.displayInfo();
        }
    }
    public void displayAppointmentsByStatus(String status) {
        System.out.println(status + " Appointments:");
        boolean found = false;
        for (Appointment appointment : appointmentList) {
            if (appointment.getStatus().equals(status)) {
                appointment.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found with status: " + status);
        }
    }
    public int countAppointmentsByStatus(String status) {
        int count = 0;
        for (Appointment appointment : appointmentList) {
            if (appointment.getStatus().equals(status)) {
                count++;
            }
        }
        return count;
    }
}
