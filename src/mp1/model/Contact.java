package mp1.model;

public class Contact {
    private String streetName;
    private int streetNumber;
    private long telephoneNumber;
    private String hospitalName;

    public Contact(String streetName, int streetNumber, long telephoneNumber
            ,String hospitalName){
        setStreetName(streetName);
        setStreetNumber(streetNumber);
        setTelephoneNumber(telephoneNumber);
        setHospitalName(hospitalName);

    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        if(streetName == null || streetName.trim().isEmpty()){
//            throw new ValException("streetName cannot be empty");
        }
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        if(streetNumber<1){
//            throw new ValException("streetNumber cannot be zero or negative");
        }
        this.streetNumber = streetNumber;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        if(hospitalName == null || hospitalName.trim().isEmpty()){
//            throw new ValException("hospitalName cannot be empty");
        }
        this.hospitalName = hospitalName;
    }

    public long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(long telephoneNumber) {
        if(telephoneNumber<000000001&&telephoneNumber>999999999){
//            throw new ValException("telephoneNumber must have exactly 9 digits");
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
