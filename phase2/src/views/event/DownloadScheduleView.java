package views.event;

import controllers.event.DownloadScheduleController;
import enums.ViewEnum;
import exceptions.html.HTMLWriteException;
import exceptions.html.OpenBrowserException;
import presenters.event.DownloadSchedulePresenter;
import views.View;

import java.util.Scanner;

/**
 *  Represents a view for Downloading event schedules
 */
public class DownloadScheduleView implements View {

    private final DownloadSchedulePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);
    private final DownloadScheduleController controller;

    public DownloadScheduleView(DownloadScheduleController controller, DownloadSchedulePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.startPrompt();
        presenter.downloadSchedulePrompt();

        boolean downloadChosen = false;
        boolean answer = false;
        while (!downloadChosen) {
            String input = userInput.nextLine();
            if (input.equals("Y")) {
                answer = true;
                downloadChosen = true;
            } else if (input.equals("N")) {
                downloadChosen = true;
            } else { presenter.invalidYesNoNotification(); }
        }
        if (answer) {
            try {
                this.controller.downloadSchedule();
                this.presenter.downloadSuccessNotification(controller.getPath());
                return ViewEnum.VOID;
            } catch (HTMLWriteException e) { // wrong while processing HTML
                presenter.htmlWriteErrorNotification();
            } catch (OpenBrowserException e) {
                presenter.openBrowserErrorNotification();
            }
        }
        this.presenter.downloadFailureNotification();
        return ViewEnum.VOID;
    }
}
