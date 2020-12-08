package controllers.event;

import exceptions.HTMLWriteErrorException;
import gateways.HTMLManager;

/**
 * Represents a controller for downloading event schedules
 */
public class DownloadScheduleController {
    protected HTMLManager hm;

    /**
     * constructs an object of DownloadScheduleController
     * @param hm html manager that contains HTML contents to be generated
     */
    public DownloadScheduleController(HTMLManager hm){
        this.hm = hm;
    }

    /**
     * Downloads a HTML and opens it in a browswer
     * @throws HTMLWriteErrorException is thrown if a HTML fails to be generated
     */
    public void downloadSchedule() throws HTMLWriteErrorException {
        hm.generateHTML();
        hm.openHTML();
    }
}
