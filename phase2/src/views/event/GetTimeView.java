package views.event;

import exceptions.PastTimeException;
import presenters.event.TimePresenter;

import java.util.Calendar;
import java.util.Scanner;

public class GetTimeView {
    private final TimePresenter timePresenter = new TimePresenter();
    private final Scanner userInput = new Scanner(System.in);
    
    protected Calendar runTimeView() {
        timePresenter.startPrompt();
        boolean valid = false;
        Calendar newTime = Calendar.getInstance();
        while (!valid) {
            try {
                timePresenter.timeYearPrompt();
                int year = Integer.parseInt(userInput.nextLine());
                timePresenter.timeMonthPrompt();
                int month = Integer.parseInt(userInput.nextLine()) - 1;
                timePresenter.timeDayPrompt();
                int day = Integer.parseInt(userInput.nextLine());
                timePresenter.timeHourPrompt();
                int hour = Integer.parseInt(userInput.nextLine());
                newTime = Calendar.getInstance();
                newTime.set(year, month, day, hour, 0, 0);
                newTime.clear(Calendar.MILLISECOND);
                if (Calendar.getInstance().compareTo(newTime) >= 0) throw new PastTimeException();
                valid = true;
            } catch (NumberFormatException e) {
                timePresenter.invalidTimeNotification();
            } catch (PastTimeException e) {
                timePresenter.pastTimeNotification();
            }
        }
        timePresenter.selectedTimeNotification(newTime);
        return newTime;
    }
}
