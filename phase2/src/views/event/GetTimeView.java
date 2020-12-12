package views.event;

import exceptions.PastTimeException;
import presenters.interfaces.TimePresenter;

import java.util.Calendar;
import java.util.Scanner;

/**
 * View responsible for getting time by year, month, day, and hour.
 */
public class GetTimeView {
    private final TimePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>GetTimeView</code> based on the following parameters
     *
     * @param presenter The given TimePresenter
     */
    public GetTimeView(TimePresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Attempt to get the time in the input. If it fails then display an error notification.
     *
     * @return the time in the input if it exists
     */
    protected Calendar runTimeView() {
        presenter.timeInputPrompt();
        boolean valid = false;
        Calendar newTime = Calendar.getInstance();
        while (!valid) {
            try {
                presenter.timeYearPrompt();
                int year = Integer.parseInt(userInput.nextLine());
                presenter.timeMonthPrompt();
                int month = Integer.parseInt(userInput.nextLine()) - 1;
                presenter.timeDayPrompt();
                int day = Integer.parseInt(userInput.nextLine());
                presenter.timeHourPrompt();
                int hour = Integer.parseInt(userInput.nextLine());
                newTime = Calendar.getInstance();
                newTime.set(year, month, day, hour, 0, 0);
                newTime.clear(Calendar.MILLISECOND);
                if (Calendar.getInstance().compareTo(newTime) >= 0) throw new PastTimeException();
                valid = true;
            } catch (NumberFormatException e) {
                presenter.invalidTimeNotification();
            } catch (PastTimeException e) {
                presenter.pastTimeNotification();
            }
        }
        presenter.selectedTimeNotification(newTime);
        return newTime;
    }
}
