package presenters.message;

import presenters.interfaces.InputErrorPresenter;

import java.util.ArrayList;
import java.util.Set;

public class ConversationPresenter implements InputErrorPresenter {
    public void conversationHeader() { System.out.println("\n[CONVERSATIONS]"); }

    public void displayConversations(Set<String> recipients) {
        System.out.println("\n[CONVERSATION RECIPIENTS]");
        System.out.println("============================================================");
        if (recipients.isEmpty()) System.out.println("{No conversations}");
        else for (String recipient : recipients) System.out.println(recipient);
        System.out.println("============================================================");
    }

    public void recipientPrompt() {
        System.out.println("To access a conversation, please enter a recipient's username:");
    }

    public void numMessagesPrompt() {
        System.out.println("How many past messages would you like to see?");
    }

    public void displayConversationMessages(String recipient, ArrayList<String> messages) {
        System.out.println("\n[YOUR CONVERSATION WITH " + recipient + " ]");
        System.out.println("============================================================");
        for (String message : messages) System.out.println(message);
        System.out.println("============================================================");
    }

    public void inputMismatchNotification() {
        System.out.println("{Sorry, the input entered was not recognized.}");
    }

    public void recipientNotFoundNotification() {
        System.out.println("{Sorry, this recipient could not be found.}");
    }

    public void noMessagesNotification() {
        System.out.println("{Sorry, you have no messages with this account.}");
    }

    public void messageNotFoundNotification() {
        System.out.println("{Sorry, a message could not be found.}");
    }

    public void contactNotFoundNotification() {
        System.out.println("{Sorry, this account is not in your contact list.}");
    }
}
