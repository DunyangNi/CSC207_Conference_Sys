package presenters.event;

import presenters.InputErrorPresenter;
import presenters.Presenter;

import java.util.ArrayList;

public class LocationPresenter implements Presenter, InputErrorPresenter {
    @Override
    public void startPrompt() { System.out.println("[LOCATION CREATION]\nWelcome to Location Creation! Follow the prompts to create your location."); }

    public void namePrompt() { System.out.println("Please enter a name for this location: "); }

    public void nameTakenNotification() { System.out.println("{Sorry, that name is already taken. Please enter another name.}"); }

    public void capacityPrompt() { System.out.println("Please enter the capacity of this location: "); }

    public void tablesPrompt() { System.out.println("Please enter the number of tables this location will hold: ");}

    public void chairsPrompt() { System.out.println("Please enter the number of chairs this location will hold: ");}

    public void internetPrompt() { System.out.println("Does this location have access to Internet (Y/N)? "); }

    public void soundSystemPrompt() { System.out.println("Does this location have a sound system (Y/N)? "); }

    public void presentationScreenPrompt() { System.out.println("Does this location have a presentation screen (Y/N)? "); }

    public void furtherNotesPrompt() { System.out.println("Please provide any additional notes, separated by a semicolon."); }

    public void displayLocations(ArrayList<String> locationStrings) {
        System.out.println("[LOCATION DIRECTORY]");
        System.out.println("=======================================================");
        for (String locationString : locationStrings) {
            System.out.println(locationString);
            System.out.println();
        }
        System.out.println("=======================================================");
    }

    @Override
    public void exitPrompt() { System.out.println("{Location creation successful!}"); }
}
