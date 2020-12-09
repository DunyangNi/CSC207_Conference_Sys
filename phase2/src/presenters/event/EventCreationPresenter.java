package presenters.event;

import presenters.InputErrorPresenter;
import presenters.Presenter;

import java.util.ArrayList;

public class EventCreationPresenter implements Presenter, InputErrorPresenter, EventErrorPresenter {

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

    public void invalidSpeakerPrompt() {
        System.out.println("One or more speakers could not be found. Ensure you have entered registered speakers.");
    }

    public void notEnoughSpeakersPrompt() {
        System.out.println("Less than two speakers were entered.");
    }

    public void topicPrompt(){
        System.out.println("Please enter the topic / name of the event: ");
    }

    public void vipOnlyPrompt() { System.out.println("Is this event restricted to VIPs (Y/N)?");}

    public void requirementsPrompt() {
        System.out.println("You will now be provided with suggested locations for your event. Please answer the following questions.");
    }

    public void locationPrompt() {
        System.out.println("Please enter the name of a location: ");
    }

    public void noSuggestedLocationsPrompt() {
        System.out.println("Sorry, there are currently no locations that meets the requirements of this event.");
    }

    public void capacityPrompt() { System.out.println("Please enter the capacity of the event: ");}

    public void tablesPrompt() { System.out.println("Please enter the number of tables this event requires: ");}

    public void chairsPrompt() { System.out.println("Please enter the number of chairs this event requires: ");}

    public void internetPrompt() { System.out.println("Does this event require access to Internet (Y/N)? "); }

    public void soundSystemPrompt() { System.out.println("Does this event require a sound system (Y/N)? "); }

    public void presentationScreenPrompt() { System.out.println("Does this event require a presentation screen (Y/N)? "); }

    public void displaySuggestedLocations(ArrayList<String> locationStrings) {
        System.out.println("[SUGGESTED LOCATIONS]");
        System.out.println("=======================================================");
        for (String locationString : locationStrings) {
            System.out.println(locationString);
            System.out.println();
        }
        System.out.println("=======================================================");
    }

    public void requirementMismatchPrompt() { System.out.println("Sorry, this location does not fit the requirements of your event."); }

    public void cancelExitPrompt() { System.out.println(" Event Creation cancelled. :("); }
}
