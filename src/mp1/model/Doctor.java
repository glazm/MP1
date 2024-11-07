package mp1.model;

import java.util.*;

public class Doctor {
    private long doctorId; //simple attribute
    private Set<String> patients = new HashSet<>();
    private Contact contact;
    private String firstName;//simple attribute
    private String surname;//simple attribute
    public static int internshipInMonths = 13;
    private Integer howManyCertificates;

    private int yearOfBirth;//simple attribute
    final static int retirementAge = 68;

    public int tillRetirement(){
        return retirementAge - yearOfBirth;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        if(contact == null){
//            throw new ValException("contact cannot be empty");
        }
        this.contact = contact;
    }
}
