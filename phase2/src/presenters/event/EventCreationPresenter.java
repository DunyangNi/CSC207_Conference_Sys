package presenters.event;

import presenters.interfaces.EventErrorPresenter;
import presenters.interfaces.InputErrorPresenter;
import presenters.interfaces.TimePresenter;

import java.util.ArrayList;

/**
 * Responsible for displaying event creation functionality prompts and notifications
 */
public class EventCreationPresenter implements InputErrorPresenter, EventErrorPresenter, TimePresenter {
    /**
     * Header of event creation prompt
     */
    public void eventCreationHeader() { System.out.println("\n[CREATE EVENT]"); }

    /**
     * Asks for event type
     */
    public void eventTypePrompt() {
        System.out.println("Please enter the type of event you wish to create (1-3):");
    }

    public void eventTypeMenu() {
        System.out.println("============================================================");
        System.out.println("1 = Networking Event      (No special requirements)");
        System.out.println("2 = Talk                  (Requires 1 speaker)");
        System.out.println("3 = Panel Discussion      (Requires 2 or more speakers)");
        System.out.println("============================================================");
    }

    /**
     * Asks for one speaker for singlespeaker (talk) case
     */
    public void singleSpeakerPrompt() {
        System.out.println("Please enter the username of the speaker: ");
    }

    /**
     * Asks for speakers for multispeaker (panel) case
     */
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

    /**
     * Asks for topic
     */

    public void topicPrompt() {
        System.out.println("Please enter the topic/name of the event: ");
    }

    /**
     * Asks for VIP restriction
     */

    public void vipOnlyPrompt() {
        System.out.println("Is this event restricted to VIPs (Y/N)?");
    }

    /**
     * Asks for location
     */

    public void locationPrompt() {
        System.out.println("Please enter the name of a location: ");
    }

    public void noSuggestedLocationsNotification() {
        System.out.println("{Sorry, there are currently no locations that meets the requirements of this event.}");
    }

    /**
     * Asks for capacity
     */

    public void capacityPrompt() {
        System.out.println("Please enter the capacity of the event: ");
    }

    /**
     * Asks for tables
     */

    public void tablesPrompt() {
        System.out.println("Please enter the number of tables this event requires: ");
    }

    /**
     * Asks for chairs
     */

    public void chairsPrompt() {
        System.out.println("Please enter the number of chairs this event requires: ");
    }

    /**
     * Asks for internet requirement
     */

    public void internetPrompt() {
        System.out.println("Does this event require access to Internet (Y/N)? ");
    }

    /**
     * Asks for sound system requirement
     */

    public void soundSystemPrompt() {
        System.out.println("Does this event require a sound system (Y/N)? ");
    }

    /**
     * Asks for presentation screen requirement
     */

    public void presentationScreenPrompt() {
        System.out.println("Does this event require a presentation screen (Y/N)? ");
    }

    /**
     * Gives suggested locations and asks for choice
     * @param locationStrings list of names of suggested locations
     */

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

    public void eventCreationSuccessNotification() {
        System.out.println("{Event created.}");
    }
}
