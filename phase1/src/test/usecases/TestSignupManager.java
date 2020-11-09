package test.usecases;

import entities.Speaker;
import entities.Organizer;
import entities.Attendee;
import entities.EventTalk;
import use_cases.SignupManager;
import java.util.Calendar;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestSignupManager {
    static Calendar d1;
    static Organizer z1;
    static Speaker s1;
    static Attendee a1;
    static Attendee a2;
    static Attendee a3;
    static EventTalk e1;

    @BeforeClass
    public static void setup() throws Exception {
        // date setup
        d1 = Calendar.getInstance();
        d1.set(2017, Calendar.APRIL, 1, 9, 7);
        // account setup
        z1 = new Organizer("hugofirst", "pass101", "Hugo", "First");
        s1 = new Speaker("chrisbacon", "pass000", "Chris", "Bacon");
        a1 = new Attendee("johndoe", "pass123", "John", "Doe");
        a2 = new Attendee("janedoe", "pass456", "Jane", "Doe");
        a3 = new Attendee("lucydoe", "pass987", "Lucy", "Doe");
        // event talk setup
        e1 = new EventTalk("topic1", d1, "auditorium", z1, s1);
    }

    @Test(timeout = 50)
    public void test_Signup1() {
        SignupManager m1 = new SignupManager();
        m1.addAttendee(e1, a1);

        String res1 = e1.toString();
        String exp1 = "1: (topic1) (2017-4-1 9:07) organizer (hugofirst) Attendees (johndoe) speaker (chrisbacon)";

        assertEquals(exp1, res1);
    }

    @Test(timeout = 50)
    public void test_Signup2() {
        SignupManager m1 = new SignupManager();
        m1.addAttendee(e1, a1);
        m1.addAttendee(e1, a2);

        String res1 = e1.toString();
        String exp1 = "1: (topic1) (2017-4-1 9:07) organizer (hugofirst) " +
                "Attendees (johndoe janedoe) speaker (chrisbacon)";

        m1.addAttendee(e1, a3);
        String res2 = e1.toString();
        String exp2 = "1: (topic1) (2017-4-1 9:07) organizer (hugofirst) " +
                "Attendees (johndoe janedoe) speaker (chrisbacon)";

        assertEquals(exp1, res1);
        assertEquals(exp2, res2);
    }

    @Test(timeout = 50)
    public void test_IsFull() {
        SignupManager m1 = new SignupManager();
        m1.addAttendee(e1, a1);
        boolean res1 = m1.isFull(e1);

        m1.addAttendee(e1, a2);
        boolean res2 = m1.isFull(e1);

        assertFalse(res1);
        assertTrue(res2);
    }

    @Test(timeout = 50)
    public void test_IsSignedUp() {
        SignupManager m1 = new SignupManager();
        m1.addAttendee(e1, a1);
        boolean res1 = m1.isSignedUp(e1, a1);
        boolean res2 = m1.isSignedUp(e1, a2);

        m1.addAttendee(e1, a2);
        boolean res3 = m1.isSignedUp(e1, a2);

        m1.addAttendee(e1, a3);
        boolean res4 = m1.isSignedUp(e1, a3);

        assertTrue(res1);
        assertFalse(res2);
        assertTrue(res3);
        assertFalse(res4);
    }
}
