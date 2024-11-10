package mp1.model;

import mp1.exception.ValException;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Doctor extends ObjectPlus implements Serializable {//extend mp1.model.ObjectPlus to have access to its methods and global extent
    private long doctorId; //simple attribute that is needed to identify doctor
    private Set<String> patients = new HashSet<>(); //repeated attribute
    private Contact contact;//complex attribute
    private String firstName;//simple attribute
    private String surname;//simple attribute
    public static int internshipInMonths = 13; //class attribute
    private Integer howManyCertificates; //optional attribute
    private int yearOfBirth;//simple attribute
    final static int retirementAge = 68; //class attribute
    public int tillRetirement(){
        return retirementAge - yearOfBirth;
    }//derived attribute

    public Doctor(long doctorId, String firstName, String surname, int yearOfBirth, Contact contact, String patient){
        super(); //use of super constructor to construct extent from mp1.model.ObjectPlus, global extent
        this.doctorId = doctorId; //assign doctorId to this specific class
        setFirstName(firstName);//use of setter for firstName
        setSurname(surname);//use of setter for surname
        setYearOfBirth(yearOfBirth);//use of setter for yearOfBirth
        setContact(contact);//use of setter for contact
        addPatient(patient);//use of method to add patient to list of patients
    }//Constructor

    public Doctor(long doctorId, String firstName, String surname, int yearOfBirth, Contact contact, String patient, Integer howManyCertificates){
        super(); //use of super constructor to construct extent from mp1.model.ObjectPlus, global extent
        this.doctorId = doctorId; //assign doctorId to this specific class
        setFirstName(firstName);//use of setter for firstName
        setSurname(surname);//use of setter for surname
        setYearOfBirth(yearOfBirth);//use of setter for yearOfBirth
        setContact(contact);//use of setter for contact
        addPatient(patient);//use of method to add patient to list of patients
        setHowManyCertificates(howManyCertificates);//use of setter for howManyCertificates
    }//overloading Constructor

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
        if(firstName == null || firstName.trim().isEmpty()){
            throw new ValException("firstName cannot be empty");
        }
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname == null || surname.trim().isEmpty()){
            throw new ValException("surname cannot be empty");
        }
        this.surname = surname;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        if(yearOfBirth < 0){//if year is less then zero then throw RuntimeException
            throw new ValException("yearOfBirth cannot be less than zero");
        }
        if(yearOfBirth> LocalDate.now().getYear()){//if yearOfBirth is reaching far into the future then throw RuntimeException
            throw new ValException("yearOfBirth cannot be higher than today's date");
        }
        this.yearOfBirth = yearOfBirth;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        if(contact == null){
            throw new ValException("contact cannot be empty");
        }
        this.contact = contact;
    }

    public Optional<Integer> getHowManyCertificates(){
        return Optional.ofNullable(howManyCertificates);//if null then return empty object
    }

    public void setHowManyCertificates(Integer howManyCertificates){
        if(howManyCertificates != null && howManyCertificates<0){//if howManyCertificates is negative then throw RuntimeException
            throw new ValException("negative value of certificates is not real");
        }
        this.howManyCertificates = howManyCertificates;
    }

    public Set<String> getPatients(){return Collections.unmodifiableSet(this.patients);}

    public void addPatient(String patient){
        if(patient == null){
            throw new ValException("patient cannot be empty");
        }
        this.patients.add(patient);
    }

    public void removePatient(String patient){
        if(this.patients.size() <2){//if there is only one patient then don't remove him
            throw new ValException("doctor must have assigned to himself at least one patient");
        }
        this.patients.remove(patient);
    }

    //class method to find all doctors assigned to one patient
    public static List<Doctor> allDoctorsOfPatient(String patient) throws ClassNotFoundException{
        File extentFile = new File("./extentFile.txt");
        Iterable<Doctor> doctorExtent = ObjectPlus.getExtent(Doctor.class);//prepare global extent to iterate

        if(extentFile.exists()) {//if extent file doesn't exist then pass this step
            if(patient == null || patient.trim().isEmpty()) {//if patient is null or after trimming string from whitespaces the string is empty then throw RuntimeException
                throw new ValException("patient cannot be empty");
            }
            else {
                Iterator<Doctor> iterator = doctorExtent.iterator();
                return StreamSupport//prepare stream for logic statement
                        .stream(doctorExtent.spliterator(), false)
                        .filter(byYear -> byYear.patients.contains(patient))//logic statement using lamba for finding all doctors assigned to one patient
                        .collect(Collectors.toList());//collect data to list
            }
        }
        throw new ValException("extent file doesn't exist");
    }

    @Override
    public String toString(){//override of toString method to prepare mp1.model.Doctor data
        if(this.howManyCertificates!=null){
            return doctorId + ", "  //for overloaded constructor
                    + howManyCertificates + ", "
                    + firstName + ", "
                    + surname + ", "
                    + yearOfBirth + ", "
                    + contact.toString() + ", "
                    + patients + ", "
                    + internshipInMonths + ", "
                    + tillRetirement() + "\n";
        }
        return doctorId + ", " //for constructor
                + firstName + ", "
                + surname + ", "
                + yearOfBirth + ", "
                + contact.toString() + ", "
                + patients + ", "
                + internshipInMonths + ", "
                + tillRetirement() + "\n";
    }
}
