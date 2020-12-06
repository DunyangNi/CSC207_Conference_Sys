package presenters.event;

import presenters.Presenter;

public class EventRegistrationPresenter implements Presenter {

    @Override
    public void startPrompt() {
        System.out.println("You need to enter the speaker's username, room, topic, and the time");
    }

    @Override
    public void exitPrompt() {
        System.out.println("Registered event successfully");
    }

    public void usernamePrompt(){
        System.out.println("Enter the speaker's username:");
    }

    public void moreUsernamePrompt() {
        System.out.println("Do you want to have more speakers(1=yes, enter anything else for no):");
    }

    public void topicPrompt(){
        System.out.println("Enter the event topic:");
    }

    public void roomPrompt(){
        System.out.println("Enter the event room:");
    }

    public void capacityPrompt() { System.out.println("Enter the capacity of the event:");}

    public void invalidTimePrompt() { System.out.println("The time is not valid, please enter again:");}

    public void invalidInputPrompt() { System.out.println("The input is not valid, please enter again:");}

    public void timePrompt(){
        System.out.println("Enter the event time:");
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



}
