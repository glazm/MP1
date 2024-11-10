package mp1.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[]args) throws ClassNotFoundException{
        File extentFile = new File("./extendFile.txt");
        if(extentFile.exists()){ //if extent file doesn't exist then pass this step
                //read extent
            try{
                ObjectInputStream objectInputStream = new ObjectInputStream(
                        new FileInputStream("./extentFile.txt")//use stream to read file
                );
                Doctor.readExtents(objectInputStream);//use method to read data from class mp1.model.Doctor
                objectInputStream.close();//close read stream
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Contact contact = new Contact("Healed",16,1234567889,"Hospital for Sick");//constructor of Contact class
        Contact contact2 = new Contact("Virus",19,987654321,"Injection Hospital");//constructor of Contact class
        Doctor doctor = new Doctor(1,"John","Sulivan",1976,contact2,"Brad Sickly",3);//overloaded constructor of mp1.model.Doctor class
        Doctor doctor2 = new Doctor(2,"Paul","Grand",1992,contact,"Brad Sickly");//constructor of mp1.model.Doctor class
        List<Doctor> doctorList = new ArrayList<>();//preparing container for mp1.model.Doctor data
        doctorList.add(doctor);//add mp1.model.Doctor to container
        doctorList.add(doctor2);//add mp1.model.Doctor to container
        List<Doctor> doctorList2 = new ArrayList<>();//preparing container for mp1.model.Doctor data
        doctorList2.add(doctor2);//add mp1.model.Doctor to container
        doctorList2.add(doctor);//add mp1.model.Doctor to container

        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./extentFile.txt"));//open stream for writing the file
            Doctor.writeExtents(objectOutputStream);//use method to write data from class mp1.model.Doctor
            objectOutputStream.close();//close write stream

            ObjectInputStream objectInputStream = new ObjectInputStream(//use stream to read file
                    new FileInputStream("./extentFile.txt")
            );
            Doctor.readExtents(objectInputStream);//use method to read data from class mp1.model.Doctor
            objectInputStream.close();//close read stream

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
