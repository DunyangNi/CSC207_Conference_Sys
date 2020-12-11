package presenters.event;

import presenters.Presenter;

import java.util.ArrayList;

public class EventPresenter implements Presenter {
    @Override
    public void startPrompt() {
    }

    public void myEventsHeader() {
        System.out.println();
        System.out.println("[MY EVENTS SCHEDULE]");
        System.out.println("============================================================");
    }

    public void allEventsHeader() {
        System.out.println();
        System.out.println("[ALL EVENTS SCHEDULE]");
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

    @Override
    public void exitPrompt() {
    }
}