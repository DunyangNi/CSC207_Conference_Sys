package use_cases;

import entities.Request;
import exceptions.not_found.ObjectNotFoundException;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Arrays;

/**
 * Represents the entire system of Requests
 */
public class RequestManager implements Serializable {
    private final HashMap<Integer, Request> unresolvedRequests = new HashMap<>();
    private final HashMap<Integer, Request> resolvedRequests = new HashMap<>();
    private int assignRequestID;

    /**
     * Sends a request.
     * @param senderUsername username of sender
     * @param requestSubjectLine subject of request
     * @param requestContent content of request
     */
    public void sendRequest(String senderUsername, String requestSubjectLine, String requestContent) {
        Calendar timesent = Calendar.getInstance();
        Request Request = new Request(timesent, senderUsername, requestSubjectLine, requestContent, assignRequestID);
        this.unresolvedRequests.put(assignRequestID, Request);

        this.assignRequestID = this.assignRequestID + 1;
    }

    /**
     * Attempts to resolve a request.
     * @param requestID ID of desired request
     * @throws ObjectNotFoundException when requestID is invalid
     */
    public void resolveRequest(Integer requestID) throws ObjectNotFoundException{
        try{
            Request request = this.unresolvedRequests.get(requestID);
            request.resolveRequest();

            this.unresolvedRequests.remove(requestID);
            this.resolvedRequests.put(requestID, request);
        }
        catch(Exception e) {
            throw new ObjectNotFoundException("request");
        }
    }

    /**
     * @return String representation of all unresolved requests
     */
    public String unresolvedRequestListToString() {
        Request[] requestArray = new Request[this.unresolvedRequests.size()];
        int updateIndex = 0;
        for(Integer id: unresolvedRequests.keySet()) {
            Request curRequest = unresolvedRequests.get(id);
            requestArray[updateIndex] = curRequest;
            updateIndex += 1;
        }

        Arrays.sort(requestArray); //sorted by time: earliest first, later last

        StringBuilder stringrep = new StringBuilder();
        for(Request request: requestArray) {
            stringrep.append(this.getRequestInfo(request));
        }

        return stringrep.toString();

    }

    /**
     * @return String representation of all resolved requests
     */
    public String resolvedRequestListToString() {
        Request[] requestArray = new Request[this.resolvedRequests.size()];
        int updateIndex = 0;
        for(Integer id: resolvedRequests.keySet()) {
            Request curRequest = resolvedRequests.get(id);
            requestArray[updateIndex] = curRequest;
            updateIndex += 1;
        }

        Arrays.sort(requestArray); //sorted by time: earliest first, later last

        StringBuilder stringrep = new StringBuilder();
        for(Request request: requestArray) {
            stringrep.append(this.getRequestInfo(request));
        }

        return stringrep.toString();
    }

    /**
     * @return String representation of a request
     * @param request desired Request
     */
    public String getRequestInfo(Request request) {
        StringBuilder requestinfo = new StringBuilder();
        requestinfo.append("Time of request: " + request.getTimeOfRequest().getTime().toString() + "\n");
        requestinfo.append("Sender username: " +  request.getSenderUsername() + "\n");
        requestinfo.append("Subject: " + request.getRequestSubjectLine() + "\n");
        requestinfo.append("Body: " + request.getRequestContent() + "\n");
        requestinfo.append("Request ID: " + request.getRequestID() + "\n");

        return requestinfo.toString();
    }
}
