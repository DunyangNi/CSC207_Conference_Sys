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

    private final DownloadScheduleController controller;
    private final DownloadSchedulePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public DownloadScheduleView(DownloadScheduleController controller, DownloadSchedulePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.startPrompt();

        try {
            this.controller.downloadSchedule();
            this.presenter.downloadSuccessNotification(controller.getPath());
        } catch (HTMLWriteException e) { // wrong while processing HTML
            presenter.htmlWriteErrorNotification();
        } catch (OpenBrowserException e) {
            presenter.openBrowserErrorNotification();
        }

        return ViewEnum.VOID;
    }
}
