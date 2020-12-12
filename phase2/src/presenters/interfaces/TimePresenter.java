package presenters.interfaces;

import java.util.Calendar;

public interface TimePresenter {
    default void timeInputPrompt() {
        System.out.println("You will now input a time slot. ");
    }

    default void timeYearPrompt() {
        System.out.println("Input a year (YYYY): ");
    }

    default void timeMonthPrompt() {
        System.out.println("Input a month (1-12): ");
    }

    default void timeDayPrompt() {
        System.out.println("Input a day of month (1-31): ");
    }

    default void timeHourPrompt() {
        System.out.println("Input an hour of day (9-16): ");
    }

    default void selectedTimeNotification(Calendar time) {
        System.out.println("{The time you have selected is " + time.getTime().toString() + " }");
    }

    default void invalidTimeNotification() {
        System.out.println("{The selected time is invalid, please enter a valid time slot.}");
    }

    default void pastTimeNotification() {
        System.out.println("{The selected time takes place in the past.}");
    }
}
