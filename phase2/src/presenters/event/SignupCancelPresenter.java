package presenters.event;

import presenters.EventErrorPresenter;
import presenters.InputErrorPresenter;
import presenters.Presenter;

public class SignupCancelPresenter implements Presenter, EventErrorPresenter, InputErrorPresenter {
    @Override
    public void startPrompt() {
        System.out.println("[CANCEL SIGN UP FOR EVENT]");
    }

    public void eventIdPrompt() {
        System.out.println("Please enter the ID of an event you no longer wish to sign up for: ");
    }

    public void attendeeNotFoundNotification() {
        System.out.println("{Sorry, this attendee could not be found.}");
    }

    public void signupCancelFailureNotification() {
        System.out.println("{No sign-ups were cancelled.}");
    }

    @Override
    public void exitPrompt() {
        System.out.println("{Successfully cancelled signing up for the event!}");
    }
}
