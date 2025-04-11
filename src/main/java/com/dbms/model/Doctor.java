package com.dbms.model;

public class Doctor {
    private final int doctorId;
    private final String name;
    private final String specialization;
    private final String availability;
    private final String contactNumber;

    // Constructor with contact number
    public Doctor(int doctorId, String name, String specialization, String availability, String contactNumber) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
        this.contactNumber = contactNumber;
    }

    // Getters
    public int getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAvailability() {
        return availability;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    // Display method
    public void displayInfo() {
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Name: " + name);
        System.out.println("Specialization: " + specialization);
        System.out.println("Availability: " + availability);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("---------------------------");
    }
}
