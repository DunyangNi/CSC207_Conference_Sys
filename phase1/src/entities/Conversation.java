package entities;

import java.util.ArrayList;

public class Conversation {
    private ArrayList<Account> messengers;
    private ArrayList<Message> messages = new ArrayList<>();

    public Conversation(ArrayList<Account> users, Message initialMessage) {
        this.messengers = users;
        this.messages.add(initialMessage);
    }

    public ArrayList<Account> getMessengers() {
        return messengers;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessengers(ArrayList<Account> messengers) {
        this.messengers = messengers;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
