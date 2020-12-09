package presenters.event;

import presenters.InputErrorPresenter;
import presenters.Presenter;

public class CancelSignupPresenter implements Presenter, EventErrorPresenter, InputErrorPresenter {
    @Override
    public void startPrompt() { System.out.println("[CANCEL SIGN UP FOR EVENT]\nPlease follow the prompts to cancel signing up for an event."); }

    public void eventIdPrompt() {
        System.out.println("Please enter the ID of an event you no longer wish to sign up for: ");
    }

    public void attendeeNotFoundPrompt() { System.out.println("Sorry, this attendee could not be found.");}

    public void cancelExitPrompt() { System.out.println("Cancel signing up cancelled. :(");}

    @Override
    public void exitPrompt() { System.out.println("Successfully cancelled signing up for the event! :)"); }
}
