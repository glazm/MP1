package mp1.model;

import mp1.exception.ValException;

import java.io.Serializable;

public class Contact extends ObjectPlus implements Serializable {
    private String streetName;//simple attribute
    private int streetNumber;//simple attribute
    private long telephoneNumber;//simple attribute
    private String hospitalName;//simple attribute

    public Contact(String streetName, int streetNumber, long telephoneNumber
            ,String hospitalName){//this constructor doesn't need super constructor because it is subclass of mp1.model.Doctor
        setStreetName(streetName);//set streetName
        setStreetNumber(streetNumber);//set streetNumber
        setTelephoneNumber(telephoneNumber);//set telephoneNumber
        setHospitalName(hospitalName);//set hospitalName

    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        if(streetName == null || streetName.trim().isEmpty()){
            throw new ValException("streetName cannot be empty");
        }
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        if(streetNumber<1){
            throw new ValException("streetNumber cannot be zero or negative");
        }
        this.streetNumber = streetNumber;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        if(hospitalName == null || hospitalName.trim().isEmpty()){
            throw new ValException("hospitalName cannot be empty");
        }
        this.hospitalName = hospitalName;
    }

    public long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(long telephoneNumber) {
        if((Long.toString(telephoneNumber)).length()!=9){
            throw new ValException("telephoneNumber must have exactly 9 digits");
        }
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return  hospitalName + " " +
                streetName + " " +
                streetNumber + " " +
                 telephoneNumber;
    }
}
