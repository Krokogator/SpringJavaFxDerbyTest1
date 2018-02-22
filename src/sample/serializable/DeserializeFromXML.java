package sample.serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializeFromXML {

    public static Object deserialize(String filename) {
        Object object = null;

        try {
            FileInputStream fos = new FileInputStream(filename+".out");
            ObjectInputStream oos = new ObjectInputStream(fos);
            object = oos.readObject();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
}
