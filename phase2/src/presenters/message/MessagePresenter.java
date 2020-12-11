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
        System.out.println("Please enter the ID of the Event whose attendees you wish to message:");
    }

    public void nextEventIdPrompt() {
        System.out.println("Add another Event?");
        System.out.println("0 = No:");
        System.out.println("1 = Yes:");
    }

    public void accountNotFoundNotification() { System.out.println("This account does not exist.");}

    public void notInContactNotification() {System.out.println("This user is not in your contact list.");}

    public void noRecipientsNotification() { System.out.println("There aren't any accounts of this type that you can message.");}

    public void eventNotFoundNotification() { System.out.println("This event does not exist.");}
    @Override
    public void exitPrompt() {
    }
}
