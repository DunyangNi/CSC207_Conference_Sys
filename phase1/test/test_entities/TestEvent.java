package test_entities;

import entities.*;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

public class TestEvent {
    static Calendar date1;
    static Calendar date2;
    static Attendee acct1;
    static Attendee  acct2;
    static SimpleDateFormat sdf;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        date1 = Calendar.getInstance();
        date1.set(2001, 02, 03, 04, 05, 01);

        date2 = Calendar.getInstance();
        date2.set(2002, 03, 04, 13, 55, 02);

        acct1 = new Attendee("Name1","pw1", "first1", "last1");
        acct2 = new Attendee("Name2","pw2", "first2", "last2");

        sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
    }

    @Test(timeout = 50)
    public void test_Event() {
        Event.resetSid();
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        Event ev2 = new Event("CLEAN arch2", date1, "New York", acct1);
        Event ev3 = new Event("CLEAN arch1", date2, "Toronto" , acct2);
        Event ev4 = new Event("CLEAN arch2", date2, "New York", acct2);
        Event ev5 = new Event("CLEAN arch2", date2, "New York", null);

        String act1 = ev1.toString();
        String act2 = ev2.toString();
        String act3 = ev3.toString();
        String act4 = ev4.toString();
        String act5 = ev5.toString();

        String exp1 = "0: (CLEAN arch1) (2001-2-3 4:5) organizer (Name1) Attendees ()";
        String exp2 = "1: (CLEAN arch2) (2001-2-3 4:5) organizer (Name1) Attendees ()";
        String exp3 = "2: (CLEAN arch1) (2002-3-4 13:55) organizer (Name2) Attendees ()";
        String exp4 = "3: (CLEAN arch2) (2002-3-4 13:55) organizer (Name2) Attendees ()";
        String exp5 = "4: (CLEAN arch2) (2002-3-4 13:55) organizer () Attendees ()";

        assertEquals(exp1, act1);
        assertEquals(exp2, act2);
        assertEquals(exp3, act3);
        assertEquals(exp4, act4);
        assertEquals(exp5, act5);
    }

    @Test(timeout = 50)
    public void test_resetSid(){
        Event.resetSid();
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        Event ev2 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        Event ev3 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        Event ev4 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        Event ev5 = new Event("CLEAN arch1", date1, "Toronto" , acct1);

        Event.resetSid();
        Event ev6 = new Event("CLEAN arch1", date1, "Toronto" , acct1);

        String act1 = ev5.getId() + "";
        String act2 = ev6.getId() + "";
        String exp1 = "4";
        String exp2 = "0";

        assertEquals(exp1, act1);
        assertEquals(exp2, act2);
    }

    @Test(timeout = 50)
    public void test_equals(){
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        Event ev2 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        Event ev3 = new Event("CLEAN arch1", date1, "Toront2" , acct1);

        String act1 = ev1.equals(ev2) + "";
        String act2 = ev1.equals(ev3) + "";
        String exp1 = "true";
        String exp2 = "false";

        assertEquals(exp1, act1);
        assertEquals(exp2, act2);
    }

    @Test(timeout = 50)
    public void test_hashCode(){
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        Event ev2 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        Event ev3 = new Event("CLEAN arch2", date1, "Toronto" , acct2);

        String act1 = (ev1.hashCode() == ev2.hashCode()) + "";
        String act2 = (ev1.hashCode() == ev3.hashCode()) + "";
        String exp1 = "true";
        String exp2 = "false";

        assertEquals(exp1, act1);
        assertEquals(exp2, act2);
    }

    @Test(timeout = 50)
    public void test_setTopic(){
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        ev1.setTopic("foo");
        String act1 =  ev1.getTopic();
        String exp1 = "foo";
        assertEquals(exp1, act1);
    }

    @Test(timeout = 50)
    public void test_setTime(){
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        ev1.setTime(date2);
        String act1 = ""+ sdf.format(ev1.getTime().getTime());
        String exp1 = "2002 Apr. 04 13:55:02";
        assertEquals(exp1, act1);
    }

    @Test(timeout = 50)
    public void test_setLocation(){
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        ev1.setLocation("USA");
        String act1 = ""+ ev1.getLocation();
        String exp1 = "USA";
        assertEquals(exp1, act1);
    }

    @Test(timeout = 50)
    public void test_setOrganizer(){
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        ev1.setOrganizer(acct2);
        String act1 = ""+ ev1.getOrganizer().getUsername();
        String exp1 = "Name2";
        assertEquals(exp1, act1);
    }

    @Test(timeout = 50)
    public void test_setAttendees(){
        Event.resetSid();
        ArrayList<Attendee> atd1 = new ArrayList<Attendee>();
        atd1.add(acct1);
        atd1.add(acct2);

        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        ev1.setAttendees(atd1);

        String act1 = "";
        String act2 = ev1.toString();
        for (Account atd: ev1.getAttendees()){
            act1 += atd.getUsername() + " ";
        }
        String exp1 = "Name1 Name2 ";
        String exp2 = "0: (CLEAN arch1) (2001-2-3 4:5) organizer (Name1) Attendees (Name1 Name2)";
        assertEquals(exp1, act1);
        assertEquals(exp2, act2);
    }

    @Test(timeout = 50)
    public void test_setId(){
        Event.resetSid();
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        ev1.setId(9);
        String act1 = ""+ ev1.getId();
        String exp1 = "9";
        assertEquals(exp1, act1);
    }

    @Test(timeout = 50)
    public void test_getTopic(){
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        String act1 = ""+ev1.getTopic();
        String exp1 = "CLEAN arch1";
        assertEquals(exp1, act1);
    }

    @Test(timeout = 50)
    public void test_getTime(){
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        String act1 = ""+ sdf.format(ev1.getTime().getTime());
        String exp1 = "2001 Mar. 03 04:05:01";
        assertEquals(exp1, act1);
    }

    @Test(timeout = 50)
    public void test_getLocation(){
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        String act1 = ""+ ev1.getLocation();
        String exp1 = "Toronto";
        assertEquals(exp1, act1);
    }

    @Test(timeout = 50)
    public void test_getOrganizer(){
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        String act1 = ""+ ev1.getOrganizer().getUsername();
        String exp1 = "Name1";
        assertEquals(exp1, act1);
    }

    @Test(timeout = 50)
    public void test_getAttendees(){
        ArrayList<Attendee> atd1 = new ArrayList<Attendee>();
        atd1.add(acct1);
        atd1.add(acct2);

        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        ev1.setAttendees(atd1);
        String act1 = "";
        for (Account atd: ev1.getAttendees()){
            act1 += atd.getUsername() + " ";
        }
        String exp1 = "Name1 Name2 ";
        assertEquals(exp1, act1);
    }

    @Test(timeout = 50)
    public void test_getId(){
        Event.resetSid();
        Event ev1 = new Event("CLEAN arch1", date1, "Toronto" , acct1);
        String act1 = ""+ ev1.getId();
        String exp1 = "0";
        assertEquals(exp1, act1);
    }
}
