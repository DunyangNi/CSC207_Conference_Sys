package entities;
import java.util.*;
public class Request {
    private Calendar timeOfRequest;
    private String senderUsername;
    private String request;
    private Boolean resolved = false;

    public Request(Calendar timeOfRequest, String senderUsername, String request) {
        this.timeOfRequest = timeOfRequest;
        this.senderUsername = senderUsername;
        this.request = request;
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
    public Boolean getResolutionSatus(){
        return this.resolved;
    }

    public void resolveRequest(){
        this.resolved = true;
    }

}
