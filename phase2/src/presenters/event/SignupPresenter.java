package presenters.event;

import presenters.EventErrorPresenter;
import presenters.InputErrorPresenter;
import presenters.Presenter;

public class SignupPresenter implements Presenter, EventErrorPresenter, InputErrorPresenter {
    @Override
    public void startPrompt() {
        System.out.println("[SIGN UP FOR EVENT]");
    }

    public void eventIdPrompt() {
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

    @Override
    public void exitPrompt() {
        System.out.println("{Successfully signed up for event!}");
    }
}
