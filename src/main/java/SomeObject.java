import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class SomeObject implements Serializable {
    String name = "mike";
    String[] hobbies = {"hiking", "biking", "smoking"};
}

class Test {
    public static void main(String[] args) {

        String filePath = System.getProperty("user.dir");
        String fileName = "test.ser";

        // ================== SERIALIZE OBJECT ==================
        SomeObject object = new SomeObject();

        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(object);
            out.close();
            file.close();

            System.out.println("object serialized to: " + filePath);
        }
        catch (IOException e) {
            System.out.println("IO Exception caught");
        }

        // ================== DE-SERIALIZE OBJECT ==================
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            SomeObject readObject = (SomeObject) in.readObject();
            in.close();
            file.close();

            System.out.println("\nobject deserialized: ");
            System.out.println("SomeObject.name = " + readObject.name);
            Arrays.stream(readObject.hobbies).forEach(x -> System.out.println("SomeObject.hobbies = " + x));
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
//        catch (FileNotFoundException e) {         // these are subclasses of IOException, so caught already. unless disjointed and put before IOException, this will be redundant
//            e.printStackTrace();
//        }
//        catch (InvalidClassException e) {
//            e.printStackTrace();
//        }
    }
}
