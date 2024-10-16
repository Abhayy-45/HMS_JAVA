public class Patient {
    private int patientId;
    private String name;
    private int age;
    private String contact;
    private String disease;
    public Patient(int patientId, String name, int age, String contact, String disease) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.disease = disease;
    }
    public int getPatientId() {
        return patientId;
    }
    public void displayInfo() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Contact: " + contact);
        System.out.println("Disease: " + disease);
        System.out.println(); 
    }
}
