package presenters.event;

import presenters.Presenter;

import java.util.Calendar;

public class TimePresenter implements Presenter {
    @Override
    public void startPrompt() {
        System.out.println("You will now input a time slot. ");
    }

    public void timeYearPrompt() {
        System.out.println("Input a year (YYYY): ");
    }

    public void timeMonthPrompt() {
        System.out.println("Input a month (1-12): ");
    }

    public void timeDayPrompt() {
        System.out.println("Input a day of month (1-31): ");
    }

    public void timeHourPrompt() {
        System.out.println("Input an hour of day (9-16): ");
    }

    public void selectedTimePrompt(Calendar time) {
        System.out.println("The time you have selected is " + time.getTime().toString());
    }

    public void invalidTimePrompt() { System.out.println("The selected time is invalid, please enter a valid time slot."); }

    public void pastTimePrompt() { System.out.println("The selected time takes place in the past."); }

    public void outOfSchedulePrompt() { System.out.println("The selected time is not from 9 AM to 4 PM."); }

    @Override
    public void exitPrompt() { }
}
