package controllers.request;

import exceptions.not_found.ObjectNotFoundException;
import gateways.DataManager;
import use_cases.request.RequestManager;

/**
 * Controls request sending, resolving and viewing
 */
public class RequestController {
    public final RequestManager rm;
    public final String username;

    /**
     * Creates an instance of <code>RequestController </code> with given parameters.
     *
     * @param dm Datamanager containing all needed managers.
     */
    public RequestController(DataManager dm) {
        this.rm = dm.getRequestManager();
        this.username = dm.getUsername();
    }

    /**
     * Attempts the resolve the request with given ID.
     *
     * @param requestID ID of the request to resolve
     * @throws ObjectNotFoundException when requestID is invalid
     */
    public void resolveRequest(Integer requestID) throws ObjectNotFoundException {
        this.rm.resolveRequest(requestID);
    }

    /**
     * Sends a request.
     *
     * @param requestSubjectLine desired request subject
     * @param requestContent     desired request content
     */
    public void sendRequest(String requestSubjectLine, String requestContent) {
        this.rm.sendRequest(this.username, requestSubjectLine, requestContent);
    }

    /**
     * @return string representation of all pending requests
     */
    public String getPendingRequestListString() {
        return this.rm.pendingRequestListToString();
    }

    /**
     * @return string representation of all resolved requests
     */
    public String getResolvedRequestsString() {
        return this.rm.resolvedRequestListToString();
    }
}
