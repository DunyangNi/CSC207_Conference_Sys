package presenters.event;

import java.util.ArrayList;

public class EventPresenter {
    public void myEventsHeader() {
        System.out.println("\n[MY EVENTS SCHEDULE]");
        System.out.println("============================================================");
    }

    public void allEventsHeader() {
        System.out.println("\n[ALL EVENTS SCHEDULE]");
        System.out.println("============================================================");
    }

    public void displayEventSchedule(ArrayList<String> selectedEvents) {
        if (selectedEvents.isEmpty()) System.out.println("{No scheduled events}");
        for (String eventString : selectedEvents) {
            System.out.println(eventString);
            System.out.println();
        }
        System.out.println("============================================================");
    }
}