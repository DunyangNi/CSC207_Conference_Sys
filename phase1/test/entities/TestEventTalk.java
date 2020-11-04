package entities;

import entities.Account;
import entities.Event;
import entities.EventTalk;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class TestEventTalk {
    static Calendar date1;
    static Calendar date2;
    static Account acct1;
    static Account acct2;
    static SimpleDateFormat sdf;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        date1 = Calendar.getInstance();
        date1.set(2001, 02, 03, 04, 05, 01);

        date2 = Calendar.getInstance();
        date2.set(2002, 03, 04, 13, 55, 02);

        acct1 = new Account("Name1", "pw1", "first1", "last1");
        acct2 = new Account("Name2", "pw2", "first2", "last2");

        sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
    }

    @Test(timeout = 50)
    public void test_EventTalk() {
        Event.resetSid();
        Event ev1 = new EventTalk("CLEAN arch1", date1, "Toronto", acct1, acct2);
        Event ev2 = new EventTalk("CLEAN arch2", date1, "New York", acct1, acct2);
        Event ev5 = new EventTalk("CLEAN arch2", date2, "New York", null, null);

        String act1 = ev1.toString();
        String act2 = ev2.toString();
        String act5 = ev5.toString();

        String exp1 = "1: (CLEAN arch1) (2001-2-3 4:5) organizer (Name1) Attendees () speaker (Name2)";
        String exp2 = "2: (CLEAN arch2) (2001-2-3 4:5) organizer (Name1) Attendees () speaker (Name2)";
        String exp5 = "3: (CLEAN arch2) (2002-3-4 13:55) organizer () Attendees () speaker ()";

        assertEquals(exp1, act1);
        assertEquals(exp2, act2);
        assertEquals(exp5, act5);
    }

    @Test(timeout = 50)
    public void test_equals() {
        Event ev1 = new EventTalk("CLEAN arch1", date1, "Toronto", acct1, acct2);
        Event ev2 = new EventTalk("CLEAN arch1", date1, "Toronto", acct1, acct2);
        Event ev3 = new EventTalk("CLEAN arch1", date1, "Toronto", acct1, acct1);

        String act1 = ev1.equals(ev2) + "";
        String act2 = ev1.equals(ev3) + "";
        String exp1 = "true";
        String exp2 = "false";

        assertEquals(exp1, act1);
        assertEquals(exp2, act2);
    }

    @Test(timeout = 50)
    public void test_hashCode() {
        Event ev1 = new EventTalk("CLEAN arch1", date1, "Toronto", acct1, acct2);
        Event ev2 = new EventTalk("CLEAN arch1", date1, "Toronto", acct1, acct2);
        Event ev3 = new EventTalk("CLEAN arch2", date1, "Toronto", acct1, acct1);

        String act1 = (ev1.hashCode() == ev2.hashCode()) + "";
        String act2 = (ev1.hashCode() == ev3.hashCode()) + "";
        String exp1 = "true";
        String exp2 = "false";

        assertEquals(exp1, act1);
        assertEquals(exp2, act2);
    }

    @Test(timeout = 50)
    public void test_getSpeaker() {
        Event.resetSid();
        EventTalk ev1 = new EventTalk("CLEAN arch1", date1, "Toronto", acct1, acct2);
        ev1.setSpeaker(acct1);
        String act1 = ev1.getSpeaker().getUsername();
        String exp1 = "Name1";
        assertEquals(exp1, act1);
    }
}

