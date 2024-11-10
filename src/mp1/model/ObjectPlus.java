package mp1.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class ObjectPlus implements Serializable {//mp1.model.ObjectPlus is abstract class because it is going to have global usage and implements interface Serializable for usage of graphs while reading or writing
    private static Map<Class, List<ObjectPlus>> allExtents = new Hashtable<>();//prepare container to contain all extent's

    public ObjectPlus(){//constructor of mp1.model.ObjectPlus class
        List<ObjectPlus> extent = null;//prepare container for local extent
        Class theClass = this.getClass();//assign theClass as Class that is going to be used as key for map container

        if(allExtents.containsKey(theClass)){//if global extent already use this class as key then do nothing with global extent
            extent = allExtents.get(theClass);
        }
        else{//else add class to global extent as new key
            extent = new ArrayList<>();
            allExtents.put(theClass, extent);
        }
        extent.add(this);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException{
        stream.writeObject(allExtents); //use stream to write global extent
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        allExtents = (Hashtable)stream.readObject(); //use stream to read global extent
    }

    public static <T> Iterable<T> getExtent(Class<T> type) throws ClassNotFoundException{
        if(allExtents.containsKey(type)){//get this specific class extent from global extent
            return (Iterable<T>) allExtents.get(type);
        }
        throw new ClassNotFoundException(
                String.format("No such extent is stored", type.toString(),allExtents.keySet())
        );
    }

    public static void showExtent(Class theClass) throws Exception{
        List<ObjectPlus> extent = null;//prepare local extent

        if(allExtents.containsKey(theClass)){//if global extent contain theClass as key then add to local extent
            extent = allExtents.get(theClass);
        }
        else {
            throw new Exception("Unknown class " + theClass);
        }

        System.out.println("Extent of the class: " + theClass.getSimpleName());

        for(Object obj : extent){//print out all extent's
            System.out.println(obj);
        }
    }
}

