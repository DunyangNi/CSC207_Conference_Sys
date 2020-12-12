package gateways;

import use_cases.request.RequestManager;

import java.io.IOException;

/**
 * Reads and saves request data and creates RequestManager
 * Fields:
 * requestPath: Path of file storing request data
 */
public class RequestDataManager implements DataReader, DataSaver {
    private final String requestPath;

    /**
     * Empty constructor. Sets path as RequestManager
     */
    public RequestDataManager() {
        this("RequestManager");
    }

    /**
     * Non-empty constructor. Sets path.
     * @param requestPath given path
     */
    public RequestDataManager(String requestPath) {
        this.requestPath = requestPath;
    }

    /**
     * From the path, attempt to read and create a RequestManager.
     * Create a new RequestManager if reading does not succeed.
     */
    public RequestManager readManager() {
        try {
            return (RequestManager) readObject(requestPath);
        } catch (IOException e) {
            System.out.println("Could not read RequestManager, creating a new RequestManager.");
            return new RequestManager();
        } catch (ClassNotFoundException e) {
            System.out.println("RequestManager not found, creating a new RequestManager.");
            return new RequestManager();
        }
    }
}
