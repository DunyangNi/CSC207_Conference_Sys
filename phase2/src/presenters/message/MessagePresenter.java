package presenters.message;

import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
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
        System.out.println("Please enter the ID of the Talk whose attendees you wish to message:");
    }

    public void nextEventIdPrompt() {
        System.out.println("Add another Talk?");
        System.out.println("0 = No:");
        System.out.println("1 = Yes:");
    }

    public void invalidIdPrompt() {
        System.out.println("Invalid ID. You are not speaking at this talk.");
    }

    public void AccountNotFoundPrompt() { System.out.println("This account does not exist.");}

    public void NoRecipientsPrompt() { System.out.println("There aren't any accounts of this type that you can message.");}

    public void EventNotFoundPrompt() { System.out.println("This event does not exist.");}
    @Override
    public void exitPrompt() {
    }
}
