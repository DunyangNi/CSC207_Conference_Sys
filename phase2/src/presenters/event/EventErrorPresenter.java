package presenters.event;

public interface EventErrorPresenter {
    default void invalidLocationPrompt() { System.out.println("Sorry, this location could not be found."); }

    default void inUseLocationPrompt() { System.out.println("Sorry, the selected location is busy at the specified time."); }

    default void speakerIsBusyPrompt() { System.out.println("Sorry, one or more of the speakers is busy at the specified time and/or another location."); }

    default void eventNotFoundPrompt() { System.out.println("Sorry, the requested event could not be found.");}

    default void outOfSchedulePrompt() { System.out.println("Sorry, the time input is outside of the conference schedule (9 AM to 4 PM)."); }

    default void invalidEventTypePrompt() { System.out.println("This type of event is invalid."); }
}
