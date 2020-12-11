package presenters.event;

import presenters.InputErrorPresenter;
import presenters.Presenter;

/**
 * Represents a presenter for downloading event schdules
 */
public class DownloadSchedulePresenter implements Presenter, InputErrorPresenter {

    @Override
    public void startPrompt() {
        System.out.println("[DOWNLOAD EVENT SCHEDULE]");
    }

    /**
     * Prompts if a user wants to download the event schedules
     */
    public void downloadSchedulePrompt() {
        System.out.println("Download entire event schedule (Y/N)? ");
    }

    /**
     * Displays successful downloading
     *
     * @param loc location of a downloaded file
     */
    public void downloadSuccessNotification(String loc) {
        System.out.println("{Successful! Downloaded to: " + loc + "}");
    }

    /**
     * Displays unsuccessful downloading
     */
    public void downloadFailureNotification() {
        System.out.println("{Download aborted}");
    }

    public void htmlWriteErrorNotification() {
        System.out.println("{Sorry, something went wrong while downloading}");
    }

    public void openBrowserErrorNotification() {
        System.out.println("{Sorry, something went wrong while opening the schedule}");
    }

    @Override
    public void exitPrompt() {
    }
}
