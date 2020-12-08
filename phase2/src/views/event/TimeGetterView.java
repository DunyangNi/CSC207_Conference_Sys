package views.event;

import presenters.Presenter;
import presenters.event.TimePresenter;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TimeGetterView {
    private TimePresenter presenter;

    public TimeGetterView(TimePresenter presenter){
        this.presenter = presenter;
    }

    /**
     * A helper method to collect standart time information
     * @return returns the specified time
     * @throws InstantiationException error instantiating the time
     */

    protected Calendar collectTimeInfo() throws InstantiationException, InputMismatchException {

        Scanner sc = new Scanner(System.in);
        presenter.timePrompt("day");
        int dayOfMonth = sc.nextInt();
        presenter.timePrompt("month");
        int month = sc.nextInt()-1;
        presenter.timePrompt("year");
        int year = sc.nextInt();
        presenter.timePrompt("hour");
        int hourOfDay = sc.nextInt();
        Calendar time = Calendar.getInstance();
        time.set(year, month, dayOfMonth, hourOfDay, 0, 0);
        time.clear(Calendar.MILLISECOND);
        presenter.exitPrompt();
        return time;
    }

    public void errorMessage(){
        presenter.errorPrompt();
    }
}
