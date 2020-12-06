package presenters.event;

import presenters.Presenter;

public class SignupPresenter implements Presenter {
    @Override
    public void startPrompt() {
    }

    public void eventIdPrompt() {
        System.out.println("Please enter the ID of the Talk");
    }

    public void invalidIdPrompt() {
        System.out.println("Invalid Talk ID.");
    }

    public void signedupEvents() {
        System.out.println("[SIGNED UP EVENTS]");
        System.out.println("===================================");
    }

    public void emptySchedulePrompt() {
        System.out.println("Nothing!");
    }

    @Override
    public void exitPrompt() {
    }
}
