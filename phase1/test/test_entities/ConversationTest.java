package test_entities;

import org.junit.*;
import static org.junit.Assert.*;
import entities.*;
import java.util.ArrayList;
import java.util.Calendar;

public class ConversationTest {

    static Account acc1;
    static Account acc2;
    static ArrayList<Account> accArr = new ArrayList<>();
    static Message msg1;
    static Message msg2;

    @Before
    public void setUp() throws Exception {
        acc1 = new Account("acc1", "pass1", "fname1", "lname1");
        acc2 = new Account("acc2", "pass2", "fname2", "lname2");
        accArr.add(acc1);
        accArr.add(acc2);
        msg1 = new Message(acc1, acc2, "message1");
        msg2 = new Message(acc1, acc1, "message2");
    }

    @Test
    public void testConversation() {
        Conversation convo1 = new Conversation(accArr, msg1);
        Conversation convo2 = new Conversation(accArr, msg2);
    }

    @Test
    public void testConversationEquals() {
        Conversation convo1 = new Conversation(accArr, msg1);
        Conversation convo2 = new Conversation(accArr, msg2);
        Conversation convo3 = new Conversation(accArr, msg1);

        assertNotEquals(convo1, convo2);
        assertEquals(convo1, convo3);
    }
}
