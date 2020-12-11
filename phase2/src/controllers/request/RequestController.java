package controllers.request;

import exceptions.not_found.ObjectNotFoundException;
import gateways.DataManager;
import use_cases.*;

public class RequestController {
    public final RequestManager rm;
    public final String username;

    public RequestController(DataManager dm) {

        this.rm = dm.getRequestManager();
        this.username = dm.getUsername();
    }

    public void resolveRequest(Integer requestID) throws ObjectNotFoundException {
        this.rm.resolveRequest(requestID);
    }

    public void sendRequest(String requestSubjectLine, String request) {
        this.rm.sendRequest(this.username, requestSubjectLine, request);
    }

    public String retrieveUnresolvedRequestListStringRep() {
        return this.rm.unresolvedRequestListToString();
    }

    public String retrieveResolvedRequestListStringRep(){
        return this.rm.resolvedRequestListToString();
    }
}
