package views.event;

import controllers.event.DownloadScheduleController;
import enums.ViewEnum;
import exceptions.html.HTMLWriteException;
import presenters.event.DownloadSchedulePresenter;
import views.factory.View;

/**
 * Represents a view for Downloading event schedules
 */
public class DownloadScheduleView implements View {

    private final DownloadScheduleController controller;
    private final DownloadSchedulePresenter presenter;

    public DownloadScheduleView(DownloadScheduleController controller, DownloadSchedulePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.downloadScheduleHeader();

        try {
            this.controller.downloadSchedule();
            this.presenter.downloadSuccessNotification(controller.getPath());
        } catch (HTMLWriteException e) {
            presenter.htmlWriteErrorNotification();
        }

        return ViewEnum.VOID;
    }
}
