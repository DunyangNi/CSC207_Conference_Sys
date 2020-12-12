package presenters.event;

import presenters.interfaces.EventErrorPresenter;
import presenters.interfaces.InputErrorPresenter;

/**
 * Responsible for displaying signup cancellation functionality prompts and notifications
 */
public class SignupCancelPresenter implements EventErrorPresenter, InputErrorPresenter {

    /**
     * Header of signup cancel prompt
     */
    public void signupCancelHeader() { System.out.println("\n[CANCEL SIGN UP FOR EVENT]"); }

    /**
     * Asks for ID of event
     */

    public void eventIDPrompt() {
        System.out.println("Please enter the ID of an event you no longer wish to sign up for: ");
    }

    public void attendeeNotFoundNotification() {
        System.out.println("{Sorry, this attendee could not be found.}");
    }

    public void signupCancelFailureNotification() {
        System.out.println("{No sign-ups were cancelled.}");
    }

    public void signupCancelSuccessNotification() {
        System.out.println("{Successfully cancelled signing up for the event!}");
    }
}
