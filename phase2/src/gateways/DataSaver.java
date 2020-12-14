package gateways;

import java.io.*;

/**
 * Allows to save data and managers (Account, Event, ...) in files
 */
public interface DataSaver {

    /**
     * Saves data in a file through a path
     * @param filePath given path
     * @param ser given Serializable
     * @throws IOException when an error occurs during saving
     */
    default void saveSerializable(String filePath, Serializable ser) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(ser);
        output.close();
        buffer.close();
        file.close();
    }

    /**
     * Save (insert word) data so that the (insert word) manager information is saved
     * @param filePath given path
     * @param manager given manager
     */
    default void saveManager(String filePath, Serializable manager) {
        try {
            saveSerializable(filePath, manager);
//            System.out.println("Saved " + managerName);
        } catch (IOException e) {
//            System.out.printf("Failed to save the %s.%n", managerName);
        }
    }
}
