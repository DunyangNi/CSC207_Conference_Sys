package gateways;

import use_cases.RequestManager;

import java.io.IOException;

public class RequestDataManager implements DataReader, DataSaver {
    private final String requestPath;

    public RequestDataManager() {
        this("LocationManager");
    }

    public RequestDataManager(String requestPath) {
        this.requestPath = requestPath;
    }

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
