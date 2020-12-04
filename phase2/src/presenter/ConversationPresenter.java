package presenter;

import java.util.ArrayList;
import java.util.Set;

public class ConversationPresenter implements ConsolePresenter {
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

    @Override
    public void exitPrompt() {

    }
}
