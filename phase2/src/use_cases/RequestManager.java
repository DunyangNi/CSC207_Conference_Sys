package use_cases;

import entities.Request;

import java.lang.reflect.Array;
import java.util.HashMap;

public class RequestManager {
    private HashMap<Integer, Request> unresolvedRequests;
    private HashMap<Integer, Request> resolvedRequests;
    private static Integer nextRequestID = 0;

    public void sendRequest(String senderUsername, String requestSubjectLine, String request) {}

    //Organizer should send message to request sender when they are done (done outside of this class)
    public void resolveRequest(Integer requestID) {}

//    public String unresolvedRequestListToString() {}
//
//    //oldest first, recent last
//    public String resolvedRequestListToString() {}
//
//    public String getRequestInfo(Integer requestID) {}
}
