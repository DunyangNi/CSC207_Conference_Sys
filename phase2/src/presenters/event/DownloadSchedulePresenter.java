package presenters.event;

import presenters.Presenter;

/**
 * Represents a presenter for downloading event schdules
 */
public class DownloadSchedulePresenter implements Presenter {

    @Override public void startPrompt() {
    }

    /**
     * Prompts if a user wants to download the event schedules
     */
    public void downloadSchedulePrompt(){
        System.out.println(
            "Download all event schedule ('Y' to continue)? ");
    }

    /**
     * Displays successful downloading
     * @param loc location of a downloaded file
     */
    public void downloadSuccessful(String loc) {
        System.out.println("Successful! downloaded: " + loc);
    }

    /**
     * Displays unsuccessful downloading
     */
    public void downloadAborted(){
        System.out.println("Download has been Aborted");
    }

    @Override public void exitPrompt() {
    }
}
