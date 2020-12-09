package controllers.request;

import exceptions.not_found.ObjectNotFoundException;
import gateways.DataManager;
import use_cases.*;

public class RequestHandleController {
    public final RequestManager rm;
    //TODO: modify gateways to accomodate requestmanager
    public RequestHandleController(DataManager dm) {
        this.rm = dm.getRequestManager();
    }

    public void resolveRequest(Integer requestID) throws ObjectNotFoundException {
        this.rm.resolveRequest(requestID);
    }
}
