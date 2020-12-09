package presenters.event;

import presenters.EventErrorPresenter;
import presenters.InputErrorPresenter;
import presenters.Presenter;

public class SignupPresenter implements Presenter, EventErrorPresenter, InputErrorPresenter {
    @Override
    public void startPrompt() { System.out.println("[SIGN UP FOR EVENT]\nPlease follow the prompts to sign up for an event."); }

    public void eventIdPrompt() {
        System.out.println("Please enter the ID of an event you wish to sign up for: ");
    }

    public void vipRestrictionPrompt() { System.out.println("Sorry, this event is restricted to VIPs.");}

    public void eventIsFullPrompt() { System.out.println("Sorry, this event is already full.");}

    public void alreadySignedUpPrompt() { System.out.println("You are already signed up for this event.");}

    public void cancelExitPrompt() { System.out.println("Sign up cancelled. :(");}

    @Override
    public void exitPrompt() { System.out.println("Successfully signed up for event! :)"); }
}
