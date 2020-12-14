package use_cases.request;

import entities.request.Request;
import exceptions.not_found.ObjectNotFoundException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Represents the entire system of Requests
 */
public class RequestManager implements Serializable {
    private final HashMap<Integer, Request> pendingRequests = new HashMap<>();
    private final HashMap<Integer, Request> resolvedRequests = new HashMap<>();
    private int assignRequestID;

    /**
     * Sends a request.
     *
     * @param senderUsername     username of sender
     * @param requestSubjectLine subject of request
     * @param requestContent     content of request
     */
    public void sendRequest(String senderUsername, String requestSubjectLine, String requestContent) {
        Calendar timesent = Calendar.getInstance();
        Request Request = new Request(timesent, senderUsername, requestSubjectLine, requestContent, assignRequestID);
        this.pendingRequests.put(assignRequestID, Request);

        this.assignRequestID = this.assignRequestID + 1;
    }

    /**
     * Attempts to resolve a request.
     *
     * @param requestID ID of desired request
     * @throws ObjectNotFoundException when requestID is invalid
     */
    public void resolveRequest(Integer requestID) throws ObjectNotFoundException {
        try {
            Request request = this.pendingRequests.get(requestID);
            request.resolveRequest();

            this.pendingRequests.remove(requestID);
            this.resolvedRequests.put(requestID, request);
        } catch (Exception e) {
            throw new ObjectNotFoundException("request");
        }
    }

    /**
     * @return String representation of all pending requests
     */
    public String pendingRequestListToString() {
        Request[] requestArray = new Request[this.pendingRequests.size()];
        return getString(requestArray, pendingRequests);
    }

    /**
     * @return String representation of all resolved requests
     */
    public String resolvedRequestListToString() {
        Request[] requestArray = new Request[this.resolvedRequests.size()];
        return getString(requestArray, resolvedRequests);
    }

    public String getString(Request[] requestArray, HashMap<Integer, Request> pendingRequests) {
        int updateIndex = 0;
        for (Integer id : pendingRequests.keySet()) {
            Request curRequest = pendingRequests.get(id);
            requestArray[updateIndex] = curRequest;
            updateIndex += 1;
        }

        Arrays.sort(requestArray); //sorted by time: earliest first, later last

        StringBuilder stringrep = new StringBuilder();
        for (Request request : requestArray) {
            stringrep.append(this.getRequestInfo(request));
        }

        return stringrep.toString();
    }

    /**
     * @param request desired Request
     * @return String representation of a request
     */
    public String getRequestInfo(Request request) {
        return "Request ID: " + request.getRequestID() + "\n" +
                "Time of request: " + request.getTimeOfRequest().getTime().toString() + "\n" +
                "Sender username: " + request.getSenderUsername() + "\n" +
                "Subject: " + request.getRequestSubjectLine() + "\n" +
                "Body: " + request.getRequestContent() + "\n"
                + "\n";
    }

    public HashMap<String, String> getRequestInfoMap(Request request) {
        HashMap<String, String> requestInfoMap = new HashMap<>();

        requestInfoMap.put("id", request.getRequestID().toString());
        requestInfoMap.put("time", request.getTimeOfRequest().getTime().toString());
        requestInfoMap.put("sender", request.getSenderUsername());
        requestInfoMap.put("subject", request.getRequestSubjectLine());
        requestInfoMap.put("body", request.getRequestContent());

        return requestInfoMap;
    }
}
