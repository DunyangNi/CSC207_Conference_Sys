package presenters.message;

import presenters.InputErrorPresenter;
import presenters.Presenter;

import java.util.ArrayList;
import java.util.Set;

public class ConversationPresenter implements Presenter, InputErrorPresenter {
    @Override
    public void startPrompt() {

    }

    public void displayConversations(Set<String> recipients) {
        if (recipients.isEmpty()) {
            System.out.println("You have no conversations.");
        } else {
            System.out.println("[CONVERSATION RECIPIENTS]");
            for (String recipient : recipients) {
                System.out.println(recipient);
            }
        }
    }

    public void recipientPrompt() {
        System.out.println("To access a conversation, please enter the recipient's username:");
    }

    public void numMessagesPrompt() {
        System.out.println("How many past messages would you like to see?");
    }

    public void displayConversationMessages(ArrayList<String> messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    public void inputMismatchNotification() {System.out.println("{Sorry, the input entered was not recognized.}");}

    public void recipientNotFoundNotification() {System.out.println("{This recipient does not exist.}");}

    public void noMessagesNotification() {System.out.println("{You have no messages with this account.}");}

    public void messageNotFoundNotification() {System.out.println("{Sorry, a message could not be found.}");}

    public void notInContactNotification() {System.out.println("[This user is not in your Contact List.]");}

    @Override
    public void exitPrompt() {

    }
}
