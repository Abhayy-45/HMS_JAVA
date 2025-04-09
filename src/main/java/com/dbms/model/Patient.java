package com.dbms.model;

public class Patient {
    private final int patientId;
    private final String name;
    private final int age;
    private final String gender;
    private final String contact;
    private final String disease;

    public Patient(int patientId, String name, int age, String gender, String contact, String disease) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.disease = disease;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    public String getDisease() {
        return disease;
    }

    public void displayInfo() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Contact: " + contact);
        System.out.println("Disease: " + disease);
        System.out.println();
    }
}
