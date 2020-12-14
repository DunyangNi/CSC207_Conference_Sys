package gateways;

import java.io.*;

/**
 * Allows to read data from a file path and create managers (Account, Event,...)
 */
public interface DataReader {

    /**
     * Reads data from a path
     * @param path given path
     * @return data from the path
     * @throws IOException when an error happens during reading
     * @throws ClassNotFoundException when an error happens during reading
     */
    default Object readObject(String path) throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(path);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
        Object ob = input.readObject();
        input.close();
        buffer.close();
        file.close();
        return ob;
    }

    /**
     * Attempt to read data and create a (insert word)Manager.
     * @return A use case class in the form of "(insert word) Manager", which will depend on the class
     * implementing this method
     *
     */
    Object readManager();
}
