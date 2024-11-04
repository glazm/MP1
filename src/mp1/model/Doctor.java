package mp1.model;

import java.util.*;

public class Doctor {
    private long doctorId;
    private Set<String> patients = new HashSet<>();
    private Contact contact;
    private String firstName;
    private String surname;
    public static int internshipInMonths = 13;

}
