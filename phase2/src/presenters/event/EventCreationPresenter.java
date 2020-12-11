package presenters.event;

import presenters.EventErrorPresenter;
import presenters.InputErrorPresenter;
import presenters.Presenter;
import presenters.TimePresenter;

import java.util.ArrayList;

public class EventCreationPresenter implements Presenter, InputErrorPresenter, EventErrorPresenter, TimePresenter {

    @Override
    public void startPrompt() {
        System.out.println();
        System.out.println("[CREATE EVENT]");
    }

    public void eventTypePrompt() {
        System.out.println("Please enter the type of event you wish to create (1-3):");
    }

    public void eventTypeMenu() {
        System.out.println("[EVENT TYPES]");
        System.out.println("============================================================");
        System.out.println("1 = Networking Event      (No special requirements)");
        System.out.println("2 = Talk                  (Requires 1 speaker)");
        System.out.println("3 = Panel Discussion      (Requires 2 or more speakers)");
        System.out.println("============================================================");
    }

    public void singleSpeakerPrompt() {
        System.out.println("Please enter the username of the speaker: ");
    }

    public void multiSpeakerPrompt() {
        System.out.println("Please enter the usernames of all speakers on separate lines: ");
        System.out.println("(Press ENTER/RETURN twice to finish)");
    }
    public void invalidSpeakerNotification() {
        System.out.println("{One or more speakers could not be found. Ensure you have entered registered speakers.}");
    }

    public void notEnoughSpeakersNotification() {
        System.out.println("{Less than two speakers were entered.}");
    }

    public void topicPrompt() {
        System.out.println("Please enter the topic/name of the event: ");
    }

    public void vipOnlyPrompt() {
        System.out.println("Is this event restricted to VIPs (Y/N)?");
    }

    public void locationPrompt() {
        System.out.println("Please enter the name of a location: ");
    }

    public void noSuggestedLocationsNotification() {
        System.out.println("{Sorry, there are currently no locations that meets the requirements of this event.}");
    }

    public void capacityPrompt() {
        System.out.println("Please enter the capacity of the event: ");
    }

    public void tablesPrompt() {
        System.out.println("Please enter the number of tables this event requires: ");
    }

    public void chairsPrompt() {
        System.out.println("Please enter the number of chairs this event requires: ");
    }

    public void internetPrompt() {
        System.out.println("Does this event require access to Internet (Y/N)? ");
    }

    public void soundSystemPrompt() {
        System.out.println("Does this event require a sound system (Y/N)? ");
    }

    public void presentationScreenPrompt() {
        System.out.println("Does this event require a presentation screen (Y/N)? ");
    }

    public void displaySuggestedLocations(ArrayList<String> locationStrings) {
        System.out.println("[SUGGESTED LOCATIONS]");
        System.out.println("============================================================");
        for (String locationString : locationStrings) {
            System.out.println(locationString);
            System.out.println();
        }
        System.out.println("============================================================");
    }

    public void requirementMismatchNotification() {
        System.out.println("{Sorry, this location does not fit the requirements of your event.}");
    }

    public void eventCreationFailureNotification() {
        System.out.println("{Event creation cancelled.}");
    }

    @Override
    public void exitPrompt() {
        System.out.println("{Event created.}");
    }
}
