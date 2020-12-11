package controllers.event;

import exceptions.html.HTMLWriteException;
import gateways.DataManager;
import gateways.HTMLManager;

/**
 * Represents a controller for downloading event schedules
 */
public class DownloadScheduleController {
    protected HTMLManager hm;

    public DownloadScheduleController(DataManager dm) {
        this.hm = dm.getHtmlManager();
    }

    /**
     * Downloads a HTML and opens it in a browswer
     *
     * @throws HTMLWriteException is thrown if a HTML fails to be generated
     */
    public void downloadSchedule() throws HTMLWriteException {
        hm.generateHTML();
        hm.openHTML();
    }

    public String getPath() {
        return hm.getDownloadLocation();
    }
}
