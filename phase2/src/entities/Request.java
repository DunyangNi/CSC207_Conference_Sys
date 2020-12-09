package entities;
import java.io.Serializable;
import java.util.*;
public class Request implements Comparable, Serializable {
    private Calendar timeOfRequest;
    private String senderUsername;
    private String requestSubjectLine;
    private String request;
    private Boolean resolved = false;
    private Integer requestID;

    public Request(Calendar timeOfRequest, String senderUsername, String request, Integer requestID) {
        this.timeOfRequest = timeOfRequest;
        this.senderUsername = senderUsername;
        this.request = request;
        this.requestID = requestID;
    }

    public Calendar getTimeOfRequest(){
        return this.timeOfRequest;
    }
    public String getSenderUsername(){
        return this.senderUsername;
    }
    public String getRequest(){
        return this.request;
    }
    public Boolean getResolutionStatus(){
        return this.resolved;
    }
    public String getRequestSubjectLine(){
        return this.requestSubjectLine;
    }
    public void resolveRequest(){
        this.resolved = true;
    }
    public Integer getRequestID() {
        return this.requestID;
    }

    public int compareTo(Object request){
        if(!(request instanceof Request)) {
            throw new RuntimeException();
        }
        return this.getTimeOfRequest().compareTo(((Request) request).getTimeOfRequest());
    }

}
