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
        sc.nextLine();
        presenter.timePrompt("month");
        int month = sc.nextInt()-1;
        sc.nextLine();
        presenter.timePrompt("year");
        int year = sc.nextInt();
        sc.nextLine();
        presenter.timePrompt("hour");
        int hourOfDay = sc.nextInt();
        sc.nextLine();
        Calendar time = Calendar.getInstance();
        try{
            time.set(year, month, dayOfMonth, hourOfDay, 0, 0);
            time.clear(Calendar.MILLISECOND);
            presenter.exitPrompt();
        }
        catch (Exception e) {
            throw new InstantiationException();
        }
        return time;
    }
}
