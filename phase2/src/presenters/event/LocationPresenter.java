package presenters.event;

import presenters.interfaces.InputErrorPresenter;

import java.util.ArrayList;

/**
 * Responsible for displaying location creation functionality prompts and notifications, and location lists
 */

public class LocationPresenter implements InputErrorPresenter {

    /**
     * Header of location creation prompt
     */
    public void locationCreationHeader() { System.out.println("\n[LOCATION CREATION]"); }

    /**
     * Asks for name
     */

    public void namePrompt() {
        System.out.println("Please enter a name for this location: ");
    }

    public void nameTakenNotification() {
        System.out.println("{Sorry, that name is already taken. Please enter another name.}");
    }

    /**
     * Asks for capacity
     */

    public void capacityPrompt() {
        System.out.println("Please enter the capacity of this location: ");
    }

    /**
     * Asks for tables
     */

    public void tablesPrompt() {
        System.out.println("Please enter the number of tables this location will hold: ");
    }

    /**
     * Asks for chairs
     */

    public void chairsPrompt() {
        System.out.println("Please enter the number of chairs this location will hold: ");
    }

    /**
     * Asks for internet existence
     */

    public void internetPrompt() {
        System.out.println("Does this location have access to Internet (Y/N)? ");
    }

    /**
     * Asks for sound system existence
     */

    public void soundSystemPrompt() {
        System.out.println("Does this location have a sound system (Y/N)? ");
    }

    /**
     * Asks for presentation screen existence
     */

    public void presentationScreenPrompt() {
        System.out.println("Does this location have a presentation screen (Y/N)? ");
    }

    /**
     * Asks for further notes
     */

    public void furtherNotesPrompt() {
        System.out.println("Please provide any additional notes, separated by a semicolon.");
    }

    public void locationCreationSuccessNotification() {
        System.out.println("{Location creation successful!}");
    }

    /**
     * Displays all locations
     * @param locationStrings list of names of all locations
     */

    public void displayLocations(ArrayList<String> locationStrings) {
        System.out.println("\n[LOCATION DIRECTORY]");
        System.out.println("============================================================");
        if (locationStrings.isEmpty()) System.out.println("{No locations available}");
        for (String locationString : locationStrings) {
            System.out.println(locationString);
            System.out.println();
        }
        System.out.println("============================================================");
    }

}
