package presenters.event;

import presenters.Presenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class EventPresenter implements Presenter {
    @Override
    public void startPrompt() { }

    public void myEventsPrompt() {
        System.out.println("[MY EVENTS SCHEDULE]");
        System.out.println("====================================");
    }

    public void allEventsPrompt() {
        System.out.println("[ALL EVENTS SCHEDULE]");
        System.out.println("====================================");
    }

    public void displayEventSchedule(ArrayList<String> selectedEvents) {
        if (selectedEvents.isEmpty()) System.out.println("{No scheduled events}");
        for(String eventString : selectedEvents) { System.out.println(eventString); System.out.println(); }
        System.out.println("====================================");
    }

    @Override
    public void exitPrompt() {
    }
}