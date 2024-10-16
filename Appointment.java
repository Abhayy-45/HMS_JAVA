public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String date;
    private String status; 
    public Appointment(int appointmentId, int patientId, int doctorId, String date) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.status = "Pending";
    }
    public int getAppointmentId() {
        return appointmentId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void displayInfo() {
        System.out.println("Appointment ID: " + appointmentId + 
                           ", Patient ID: " + patientId + 
                           ", Doctor ID: " + doctorId + 
                           ", Date: " + date + 
                           ", Status: " + status); 
    }
}
