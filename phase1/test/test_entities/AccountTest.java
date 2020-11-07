package test_entities;

import org.junit.*;
import static org.junit.Assert.*;
import entities.*;
import java.util.ArrayList;
import java.util.Calendar;

public class AccountTest {

    static Account acc1;
    static Account acc2;
    static ArrayList<Account> accArr12 = new ArrayList<>();
    static Message msg1;
    static Message msg2;
    static Conversation convo1;
    static Conversation convo2;
    static EventTalk event1;
    static EventTalk event2;

    @Before
    public void setUp() {
        acc1 = new Account("acc1", "pass1", "fname1", "lname1");
        acc2 = new Account("acc2", "pass2", "fname2", "lname2");
        accArr12.add(acc1);
        accArr12.add(acc2);
        msg1 = new Message(acc1, acc2, "message1");
        msg2 = new Message(acc1, acc1, "message2");
        convo1 = new Conversation(accArr12, msg1);
        convo2 = new Conversation(accArr12, msg2);
        event1 = new EventTalk("CLEAN arch1", Calendar.getInstance(), "Toronto" , acc1, acc2);
        event2 = new EventTalk("CLEAN arch2", Calendar.getInstance(), "New York", acc2, acc1);
    }

    @Test
    public void testAccount() {
        Account account1 = new Account("accuser1", "pass1", "fname1", "lname1");
        Account account2 = new Account("accuser2", "pass2", "fname2", "lname2");

        Attendee att1 = new Attendee("attuser1", "pass1", "fname1", "lname1" );
        Account att2 = new Attendee("attuser2", "pass2", "fname2", "lname2" );

        Speaker speak1 = new Speaker("speakuser1", "pass1", "fname1", "lname1" );
        Account speak2 = new Speaker("speakuser2", "pass2", "fname2", "lname2" );

        Organizer org1 = new Organizer("orguser1", "pass1", "fname1", "lname1" );
        Account org2 = new Organizer("orguser2", "pass2", "fname2", "lname2" );

        account1.getFriendsList().put(acc1.getUsername(), acc1);
        account1.getConversations().put(convo1.getMessengers().toString(), convo1);
        account1.getAttendeeTalks().add(event1);
    }

    @Test
    public void testAccountEquals() {
        Account account1 = new Account("accuser1", "pass1", "fname1", "lname1");
        Account account2 = new Account("accuser2", "pass2", "fname2", "lname2");

        Account account3 = new Account("accuser1", "pass1", "fname1", "lname1");
        Attendee att1 = new Attendee("accuser1", "pass1", "fname1", "lname1" );
        Speaker speak1 = new Speaker("accuser1", "pass1", "fname1", "lname1" );
        Organizer org1 = new Organizer("accuser1", "pass1", "fname1", "lname1" );

        assertNotEquals(account1, account2);
        assertEquals(account1, account3);
        assertEquals(account1, att1);
        assertEquals(account1, speak1);
        assertEquals(account1, org1);
    }
}
