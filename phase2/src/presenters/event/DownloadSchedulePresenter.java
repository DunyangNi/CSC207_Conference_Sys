package presenters.event;

import presenters.InputErrorPresenter;
import presenters.Presenter;

/**
 * Represents a presenter for downloading event schdules
 */
public class DownloadSchedulePresenter implements Presenter, InputErrorPresenter {
    /**
     * Header of download prompt
     */
    @Override
    public void startPrompt() {
        System.out.println();
        System.out.println("[DOWNLOAD EVENT SCHEDULE]");
    }

    /**
     * Displays successful downloading
     *
     * @param loc location of a downloaded file
     */
    public void downloadSuccessNotification(String loc) {
        System.out.println("{Downloaded to: " + loc + ".}");
    }

    public void htmlWriteErrorNotification() {
        System.out.println("{Something went wrong while downloading the schedule}");
    }

    @Override
    public void exitPrompt() {
    }
}
