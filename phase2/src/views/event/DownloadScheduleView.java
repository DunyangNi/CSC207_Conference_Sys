package views.event;

import controllers.event.DownloadScheduleController;
import exceptions.HTMLWriteErrorException;
import gateways.DataManager;
import gateways.HTMLManager;
import presenters.event.DownloadSchedulePresenter;
import use_cases.event.EventManager;
import java.util.Scanner;

/**
 *  Represents a view for Downloading event schedules
 */
public class DownloadScheduleView {

    private final DownloadSchedulePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);
    private final DownloadScheduleController dlsController;
    private final HTMLManager hm;

    /**
     * Constructs an object for DownloadScheduleView
     * @param dm data manger
     */
    public DownloadScheduleView(DownloadScheduleController controller, DownloadSchedulePresenter presenter, HTMLManager html) {
        this.hm = html;
        this.dlsController = controller;
        this.presenter = presenter;
    }

    /**
     * Displays view for downloading all event schedules
     */
    public void runView() {
        presenter.downloadSchedulePrompt();
        String command = userInput.nextLine();

        if (command.equalsIgnoreCase("Y")) {
            try {
                this.dlsController.downloadSchedule();
                this.presenter.downloadSuccessful(hm.getDownloadLocation());
            } catch (HTMLWriteErrorException e) { // wrong while processing HTML
                e.printStackTrace();
            }
        } else {
            this.presenter.downloadAborted();
        }
    }
}
