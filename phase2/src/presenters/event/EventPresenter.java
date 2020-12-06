package presenters.event;

import presenters.Presenter;

import java.util.Calendar;
import java.util.HashMap;

public class EventPresenter implements Presenter {
    @Override
    public void startPrompt() {
    }


    public void eventCreationPrompt(String field) {
        switch (field) {
            case "topic":
                System.out.println("Enter the event topic:");
                break;
            case "speaker":
                System.out.println("Enter the speaker's username:");
                break;
            case "room":
                System.out.println("Enter the event room:");
                break;
            case "capacity":
                System.out.println("Enter event capacity");
                break;
            case "vip":
                System.out.println("0 = Standard");
                System.out.println("1 = VIP");
                break;
        }
    }

    public void timePrompt(String field) {
        switch (field) {
            case "day":
                System.out.println("Day of the month (1-31)");
                break;
            case "month":
                System.out.println("Month (1-12)");
                break;
            case "year":
                System.out.println("Year (YYYY)");
                break;
            case "hour":
                System.out.println("Hour of the day (9-16)");
                break;
        }
    }

    public void eventIdPrompt() {
        System.out.println("Please enter the ID of a talk you wish to modify: ");
    }

    public void myEventsPrompt() {
        System.out.println("[MY EVENTS SCHEDULE]");
        System.out.println("===================================");
    }

    public void allEventsPrompt() {
        System.out.println("[ALL EVENTS SCHEDULE]");
        System.out.println("===================================");
    }

    // TODO: 12/06/20 Refactor these Talk methods
    public void displayTalkSchedule(HashMap<String[], Calendar> talks) {
        if (talks.keySet().isEmpty()) {
            System.out.println("No scheduled talks");
        }
        Calendar timeNow = Calendar.getInstance();
        for(String[] eventInfo : talks.keySet()) {
            if(timeNow.compareTo(talks.get(eventInfo)) < 0) {
                displayTalkInfo(eventInfo);
            }
        }
        System.out.println("===================================");
    }

    private void displayTalkInfo(String[] eventInfo) {
        System.out.println("ID: " + eventInfo[4]);
        System.out.println("Topic: " + eventInfo[0]);
        System.out.println("Speaker: " + eventInfo[1]);
        System.out.println("Location: " + eventInfo[2]);
        System.out.println("Time: " + eventInfo[3]);
        System.out.println();
    }

    @Override
    public void exitPrompt() {
    }
}