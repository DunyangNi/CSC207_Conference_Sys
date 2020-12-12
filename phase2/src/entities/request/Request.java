package entities.request;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a request that can be sent by an attendee.
 */
public class Request implements Comparable<Request>, Serializable {
    private final Calendar timeOfRequest;
    private final String senderUsername;
    private final String requestSubjectLine;
    private final String requestContent;
    private Boolean resolved = false;
    private final Integer requestID;

    /**
     * Creates an instance of <code>Request</code> with given Strings of information.
     *
     * @param timeOfRequest      time that the Request is created
     * @param senderUsername     username of sender
     * @param requestSubjectLine the subject of the request
     * @param requestContent     content of the request
     * @param requestID          ID of request. Can not be changed
     */
    public Request(Calendar timeOfRequest, String senderUsername, String requestSubjectLine, String requestContent,
                   Integer requestID) {
        this.timeOfRequest = timeOfRequest;
        this.senderUsername = senderUsername;
        this.requestSubjectLine = requestSubjectLine;
        this.requestContent = requestContent;
        this.requestID = requestID;
    }

    /**
     * @return time of request
     */
    public Calendar getTimeOfRequest() { return this.timeOfRequest; }

    /**
     * @return sender username
     */
    public String getSenderUsername() { return this.senderUsername; }

    /**
     * @return request content
     */
    public String getRequestContent() { return this.requestContent; }

    /**
     * @return resolved status
     */
    public Boolean getResolutionStatus() { return this.resolved; }

    /**
     * @return request subject line
     */
    public String getRequestSubjectLine() { return this.requestSubjectLine; }

    /**
     * @return request ID
     */
    public Integer getRequestID() {
        return this.requestID;
    }

    /**
     * Sets resolved to True
     */
    public void resolveRequest() { this.resolved = true; }

    /**
     * Compares for equality with another <code>Request</code>.
     *
     * @param request a <code>Request</code> to compare with
     * @return True iff request is an instance of Request and have the same time.
     */
    public int compareTo(Request request) {
        return this.getTimeOfRequest().compareTo(request.getTimeOfRequest());
    }
}
