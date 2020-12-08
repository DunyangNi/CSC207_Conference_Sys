package presenters.event;

import presenters.Presenter;

public class EventCreationPresenter implements Presenter {

    @Override
    public void startPrompt() {
        System.out.println("You need to enter the speaker's username, room, topic, and the time");
    }

    public void topicPrompt(){
        System.out.println("Enter the event's topic:");
    }

    public void speakerPrompt(){
        System.out.println("Enter the speaker's username:");
    }

    public void moreSpeakersPrompt() {
        System.out.println("Add more speakers?");
        System.out.println("0 = No:");
        System.out.println("1 = Yes:");
    }

    public void roomPrompt(){
        System.out.println("Enter the event's room:");
    }

    public void capacityPrompt() {
        System.out.println("Enter the event's capacity:");
    }

    public void vipPrompt() {
        System.out.println("VIP event?");
        System.out.println("0 = No");
        System.out.println("1 = Yes:");
    }

    public void timePrompt(){
        System.out.println("Enter the event's time:");
    }

    public void dayPrompt() {
        System.out.println("Day of the month (1-31)");
    }

    public void monthPrompt() {
        System.out.println("Month (1-12)");
    }

    public void yearPrompt() {
        System.out.println("Year (YYYY)");
    }

    public void hourPrompt() {
        System.out.println("Hour of the day (9-16)");
    }

    public void invalidTimePrompt() {
        System.out.println("{Invalid time, please try again}");
    }

    public void invalidInputPrompt() {
        System.out.println("{Invalid input, please try again}");
    }

    public void ErrorMessage(String type){
        switch(type){
            case "LocationNotFound":
                System.out.println("This location is not found.");break;
            case "InvalidEventType":
                System.out.println("The type is not valid.");break;
            case "LocationInUse":
                System.out.println("This location has been booked.");break;
            case "PastTime":
                System.out.println("The time has passed");
            case "InvalidTime":
                System.out.println("You cannot have event at this time.");
        }
    }

    @Override
    public void exitPrompt() {
        System.out.println("{Creating... Created}");
    }
}
