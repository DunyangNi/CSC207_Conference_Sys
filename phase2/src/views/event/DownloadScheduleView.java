package views.event;

import controllers.event.DownloadScheduleController;
import enums.ViewEnum;
import exceptions.html.HTMLWriteException;
import presenters.event.DownloadSchedulePresenter;
import views.factory.View;

/**
 * Represents a view for Downloading event schedules
 * Fields:
 * controller: DownloadScheduleController responsible for downloading schedule
 * presenter: DownloadSchedulePresenter responsible for displaying relevant prompts
 */
public class DownloadScheduleView implements View {

    private final DownloadScheduleController controller;
    private final DownloadSchedulePresenter presenter;

    /**
     * Constructs an instance of <code>DownloadScheduleView</code> based on the following parameters
     * @param controller The given DownloadScheduleController
     * @param presenter The given DownloadSchedulePresenter
     */

    public DownloadScheduleView(DownloadScheduleController controller, DownloadSchedulePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Run the view.
     * @return ViewEnum.VOID
     */

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
