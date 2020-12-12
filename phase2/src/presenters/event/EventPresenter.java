package presenters.event;

import presenters.Presenter;

import java.util.ArrayList;

/**
 * Responsible for displaying personal and global event schedules
 */
public class EventPresenter implements Presenter {
    @Override
    public void startPrompt() {
    }

    /**
     * Header of personal events schedule
     */
    public void myEventsHeader() {
        System.out.println();
        System.out.println("[MY EVENTS SCHEDULE]");
        System.out.println("============================================================");
    }

    /**
     * Header of global events schedule
     */
    public void allEventsHeader() {
        System.out.println();
        System.out.println("[ALL EVENTS SCHEDULE]");
        System.out.println("============================================================");
    }

    /**
     * Body of both event schedules
     */
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