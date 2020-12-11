package controllers.request;

import exceptions.not_found.ObjectNotFoundException;
import gateways.DataManager;
import use_cases.*;

public class RequestController {
    public final RequestManager rm;

    public RequestController(DataManager dm) {
        this.rm = dm.getRequestManager();
    }

    public void resolveRequest(Integer requestID) throws ObjectNotFoundException {
        this.rm.resolveRequest(requestID);
    }

    public void sendRequest(String senderUsername, String requestSubjectLine, String request) {
        this.rm.sendRequest(senderUsername, requestSubjectLine, request);
    }

    public String retrieveUnresolvedRequestListStringRep() {
        return this.rm.unresolvedRequestListToString();
    }

    public String retrieveResolvedRequestListStringRep(){
        return this.rm.resolvedRequestListToString();
    }
}
