package controllers.request;

import gateways.DataManager;
import use_cases.*;

public class RequestSendController {
    public final RequestManager rm;
    //TODO: modify gateways to accomodate requestmanager
    public RequestSendController(DataManager dm) {
        this.rm = dm.getRequestManager();
    }


    public void sendRequest(String senderUsername, String requestSubjectLine, String request) {
        this.rm.sendRequest(senderUsername, requestSubjectLine, request);
    }
}
