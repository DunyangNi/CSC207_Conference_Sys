package test.usecases;

import entities.Account;
import entities.Message;
import entities.Conversation;
import use_cases.ConversationManager;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import java.util.HashMap;

public class TestConversationManager {
    static Account a1;
    static Account a2;
    static Account a3;
    static ArrayList<Account> messengers1;
    static ArrayList<Account> messengers2;
    static HashMap<String, Account> friendList1;
    static HashMap<String, Account> friendList2;
    static HashMap<String, Conversation> conversations1;
    static HashMap<String, Conversation> conversations2;
    static Conversation c1;
    static Message m1;
    static Message m2;
    static Message m3;
    static Message m4;

    @BeforeClass
    public static void setup() throws Exception {
        // account setup
        a1 = new Account("johndoe", "pass123", "John", "Doe");
        a2 = new Account("janedoe", "pass456", "Jane", "Doe");
        a3 = new Account("lucydoe", "pass987", "Lucy", "Doe");
        // friend setup
        friendList1 = new HashMap<>(); friendList2 = new HashMap<>();
        friendList1.put(a2.getUsername(), a2); friendList2.put(a1.getUsername(), a1);
        a1.setFriendsList(friendList1); a2.setFriendsList(friendList2);
        // message setup
        messengers1 = new ArrayList<>();
        messengers2 = new ArrayList<>();
        messengers1.add(a1); messengers1.add(a2);
        messengers2.add(a1); messengers2.add(a3);
        m1 = new Message(a1, a2, "How are you?");
        m2 = new Message(a2, a1, "I am fine.", m1);
        m3 = new Message(a2, a3, "Hi, you good?");
        m4 = new Message(a3, a1, "No, how are YOU?");
        // conversation setup
        c1 = new Conversation(messengers1, m1);
        conversations1 = new HashMap<>(); conversations2 = new HashMap<>();
        conversations1.put(a2.getUsername(), c1); conversations2.put(a1.getUsername(), c1);
    }

    @Test(timeout = 50)
    public void test_NewConversation1() {
        // two accounts in each other's friend list message for the first time without existing conversation.
        ConversationManager.sendMessage(a1, a2, "How are you?");
        boolean res1 = a1.getConversations().get(a2.getUsername()).getMessages().get(0).equals(m1);
        boolean res2 = a2.getConversations().get(a1.getUsername()).getMessages().get(0).equals(m1);
        assertTrue(res1);
        assertTrue(res2);
    }

    @Test(timeout = 50)
    public void test_NewConversation2() {
        // a conversation should not appear if two accounts are not in each other's friend list.
        ConversationManager.sendMessage(a1, a3, "How are you?");
        boolean res1 = a1.getConversations().size() == 0 && a3.getConversations().size() == 0;
        assertTrue(res1);
    }

    @Test(timeout = 50)
    public void test_ExistingConversation() {
        // send a message to an existing conversation.
        a1.setConversations(conversations1); a2.setConversations(conversations2);
        ConversationManager.sendMessage(a2, a1, "I am fine.");
        boolean res1 = c1.getMessages().get(1).equals(m2);
        boolean res2 = c1.getMessages().get(0).getMsgToReply().equals(m1);
        assertTrue(res1);
        assertTrue(res2);
    }

    @Test(timeout = 50)
    public void test_AddMessageToConversation() {
        // internal test: add existing message to existing conversation
        ConversationManager.addMessageToConversation(c1, m2);
        boolean res1 = c1.getMessages().get(1).equals(m2);
        assertTrue(res1);
    }

    @Test(timeout = 50)
    public void test_ValidRecipient() {
        // internal test: add existing message to existing conversation
        boolean res1 = ConversationManager.validRecipient(a1, a2);
        boolean res2 = ConversationManager.validRecipient(a1, a3);
        assertTrue(res1);
        assertFalse(res2);
    }

}
