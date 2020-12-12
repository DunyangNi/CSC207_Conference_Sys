package presenters.interfaces;

public interface EventErrorPresenter {
    default void invalidLocationNotification() { System.out.println("{Sorry, this location could not be found.}"); }

    default void inUseLocationNotification() { System.out.println("{Sorry, the selected location is busy at the specified time.}"); }

    default void speakerIsBusyNotification() { System.out.println("{Sorry, one or more of the speakers is busy at the specified time and/or another location.}"); }

    default void eventNotFoundNotification() { System.out.println("{Sorry, the requested event could not be found.}");}

    default void outOfScheduleNotification() { System.out.println("{Sorry, the time input is outside of the conference schedule (9 AM to 4 PM).}"); }

    default void invalidEventTypeNotification() { System.out.println("{This type of event is invalid.}"); }
}
