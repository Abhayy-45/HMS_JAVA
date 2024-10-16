public class Doctor {
    private int doctorId;
    private String name;
    private String specialization;
    private String schedule;
    public Doctor(int doctorId, String name, String specialization, String schedule) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.schedule = schedule;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void displayInfo() {
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Name: " + name);
        System.out.println("Specialization: " + specialization);
        System.out.println("Schedule: " + schedule);
        System.out.println(); 
    }
}
