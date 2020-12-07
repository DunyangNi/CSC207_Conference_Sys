package gateways;

import java.io.*;

public interface DataSaver {
    default void saveSerializable(String filePath, Serializable ser) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(ser);
        output.close();
        buffer.close();
        file.close();
    }

    default void saveManager(String managerName, String filePath, Serializable manager) {
        try {
            saveSerializable(filePath, manager);
//            System.out.println("Saved " + managerName);
        } catch (IOException e) {
//            System.out.printf("Failed to save the %s.%n", managerName);
        }
    }
}
