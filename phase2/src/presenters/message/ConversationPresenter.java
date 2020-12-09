package presenters.message;

import presenters.Presenter;

import java.util.ArrayList;
import java.util.Set;

public class ConversationPresenter implements Presenter {
    @Override
    public void startPrompt() {

    }

    public void conversationsPrompt(Set<String> recipients) {
        if (recipients.isEmpty()) {
            System.out.println("You have no conversations.");
        } else {
            System.out.println("[CONVERSATION RECIPIENTS]");
            for (String recipient : recipients) {
                System.out.println(recipient);
            }
        }
    }

    public void usernamePrompt() {
        System.out.println("To access a conversation, please enter the recipient's username:");
    }

    public void numMessagesPrompt() {
        System.out.println("How many past messages would you like to see?");
    }

    public void conversationMessages(ArrayList<String> messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    public void InputMismatchPrompt() {System.out.println("Error: Input mismatch.");}

    public void NonPositiveIntegerPrompt() {System.out.println("Don't enter a negative number. It doesn't make sense");}

    public void NullPointerExceptionPrompt() {System.out.println("Something went wrong and we recived a NullPointerException.");}

    public void UserNotFoundPrompt() {System.out.println("This account does not exist.");}

    public void RecipientNotFoundPrompt() {System.out.println("This recipient does not exist.");}

    public void NoMessagesPrompt() {System.out.println("There are no messages with this account.");}

    public void MessageNotFoundPrompt() {System.out.println("Could not find this message.");}

    @Override
    public void exitPrompt() {

    }
}
