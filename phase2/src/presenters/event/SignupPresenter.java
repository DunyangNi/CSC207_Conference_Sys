package presenters.event;

import presenters.interfaces.EventErrorPresenter;
import presenters.interfaces.InputErrorPresenter;

/**
 * Responsible for displaying event signup functionality prompts and notifications
 */

public class SignupPresenter implements EventErrorPresenter, InputErrorPresenter {

    /**
     * Header of signup prompt
     */

    public void signupHeader() { System.out.println("\n[SIGN UP FOR EVENT]"); }

    /**
     * Ask for ID of event to signup to
     */

    public void eventIDPrompt() {
        System.out.println("Please enter the ID of an event you wish to sign up for: ");
    }

    public void vipRestrictionNotification() {
        System.out.println("{Sorry, this event is restricted to VIPs.}");
    }

    public void eventIsFullNotification() {
        System.out.println("{Sorry, this event is already full.}");
    }

    public void alreadySignedUpNotification() {
        System.out.println("{You are already signed up for this event.}");
    }

    public void signupFailureNotification() {
        System.out.println("{Sign-up cancelled.}");
    }

    public void signupSuccessNotification() {
        System.out.println("{Successfully signed up for event!}");
    }
}
