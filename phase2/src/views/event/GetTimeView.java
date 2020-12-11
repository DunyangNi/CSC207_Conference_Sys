package views.event;

import exceptions.PastTimeException;
import presenters.TimePresenter;

import java.util.Calendar;
import java.util.Scanner;

public class GetTimeView {
    private final TimePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public GetTimeView(TimePresenter presenter) {
        this.presenter = presenter;
    }

    protected Calendar runTimeView() {
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
