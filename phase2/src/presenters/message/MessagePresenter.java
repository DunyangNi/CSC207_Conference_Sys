package presenters.message;

import presenters.Presenter;

public class MessagePresenter implements Presenter {
    @Override
    public void startPrompt() {
    }

    public void usernamePrompt() {
        System.out.println("Please enter the username of the user you wish to message:");
    }

    public void messagePrompt() {
        System.out.println("Please enter the message you want to send:");
    }

    public void eventIdPrompt() {
        System.out.println("Please enter the ID of a Talk you are giving:");
    }

    public void nextEventIdPrompt() {
        System.out.println("Would you like to add another Talk?");
        System.out.println("0 = No:");
        System.out.println("1 = Yes:");
    }

    public void invalidIdPrompt() {
        System.out.println("Invalid ID. You are not speaking at this talk.");
    }

    @Override
    public void exitPrompt() {
    }
}
