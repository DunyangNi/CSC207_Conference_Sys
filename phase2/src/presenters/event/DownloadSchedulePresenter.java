package presenters.event;

import presenters.interfaces.InputErrorPresenter;

/**
 * Represents a presenter for downloading event schdules
 */
public class DownloadSchedulePresenter implements InputErrorPresenter {
    public void downloadScheduleHeader() { System.out.println("\n[DOWNLOAD EVENT SCHEDULE]"); }

    /**
     * Displays successful downloading
     *
     * @param loc location of a downloaded file
     */
    public void downloadSuccessNotification(String loc) {
        System.out.println("{Downloaded to: " + loc + ".}");
    }

    public void htmlWriteErrorNotification() {
        System.out.println("{Something went wrong while downloading the schedule.}");
    }
}
