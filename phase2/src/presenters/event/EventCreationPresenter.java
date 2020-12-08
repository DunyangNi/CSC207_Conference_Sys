package presenters.event;

import presenters.Presenter;

import java.util.Calendar;

public class EventCreationPresenter implements Presenter {

    @Override
    public void startPrompt() {
        System.out.println("[EVENT CREATION]\nWelcome to Event Creation! Follow the prompts to create your event.");
    }

    @Override
    public void exitPrompt() {
        System.out.println("Event Creation successful! Thank you! :)");
    }

    // (NEW!)
    public void eventTypePrompt() {
        System.out.println("Please enter the type of event you wish to create (1-3):");
    }

    // (NEW!)
    public void eventTypeMenu() {
        System.out.println("[EVENT TYPES]");
        System.out.println("=======================================================");
        System.out.println("1 = Networking Event      (No special requirements)");
        System.out.println("2 = Talk                  (Requires 1 speaker)");
        System.out.println("3 = Panel Discussion      (Requires 2 or more speakers)");
        System.out.println("=======================================================");
    }

    // (NEW!)
    public void singleSpeakerPrompt() {
        System.out.println("Please enter the username of the speaker: ");
    }

    // (NEW!)
    public void multiSpeakerPrompt() {
        System.out.println("Please enter the usernames of all speakers on separate lines\n(press ENTER/RETURN twice to finish): ");
    }

    // (NEW!)
    public void invalidSpeakerPrompt(String speaker) {
        System.out.println("The speaker " + speaker + " could not be found.");
    }

    public void notEnoughSpeakersPrompt() {
        System.out.println("Less than two speakers were entered.");
    }

    public void topicPrompt(){
        System.out.println("Please enter the topic / name of the event: ");
    }

    public void timePrompt(){
        System.out.println("You will now input a time slot. ");
    }

    public void timeYearPrompt() {
        System.out.println("Input a year (YYYY): ");
    }

    public void timeMonthPrompt() {
        System.out.println("Input a month (1-12): ");
    }

    public void timeDayPrompt() {
        System.out.println("Input a day of month (1-31): ");
    }

    public void timeHourPrompt() {
        System.out.println("Input an hour of day (9-16): ");
    }

    public void selectedTimePrompt(Calendar time) {
        System.out.println("The time you have selected is " + time.getTime().toString());
    }

    // (NEW!)
    public void capacityPrompt() { System.out.println("Please enter the capacity of the event: ");}

    // (NEW!)
    public void vipOnlyPrompt() { System.out.println("Is this event restricted to VIPs (Y/N)?");}


    // TODO: TO BE REDONE
    public void locationPrompt() {
        System.out.println("Please enter the name of the location: ");
    }

    public void invalidLocationPrompt() { System.out.println("The selected location could not be found."); }

    public void inUseLocationPrompt() { System.out.println("The selected location is busy at the specified time."); }

    // (NEW!)
    public void invalidEventTypePrompt() {
        System.out.println("This type of event is invalid.");
    }

    public void invalidTimePrompt() { System.out.println("The selected time is invalid, please enter a valid time slot."); }

    public void pastTimePrompt() { System.out.println("The selected time takes place in the past."); }

    public void invalidInputPrompt() { System.out.println("Invalid input, please enter again:");}

    // (NEW!)
    public void invalidCapacityPrompt() { System.out.println("The capacity is invalid, please enter a valid capacity: ");}
}
