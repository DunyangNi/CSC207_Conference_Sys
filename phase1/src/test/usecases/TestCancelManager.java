package test.usecases;

import entities.Speaker;
import entities.Organizer;
import entities.Attendee;
import entities.EventTalk;
import use_cases.SignupManager;
import use_cases.CancelManager;
import java.util.Calendar;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestCancelManager {
    static Calendar d1;
    static Organizer z1;
    static Speaker s1;
    static Attendee a1;
    static EventTalk e1;
    static SignupManager m1;

    @BeforeClass
    public static void setup() throws Exception {
        // date setup
        d1 = Calendar.getInstance();
        d1.set(2017, Calendar.APRIL, 1, 9, 7);
        // account setup
        z1 = new Organizer("hugofirst", "pass101", "Hugo", "First");
        s1 = new Speaker("chrisbacon", "pass000", "Chris", "Bacon");
        a1 = new Attendee("johndoe", "pass123", "John", "Doe");
        // event talk setup
        e1 = new EventTalk("topic1", d1, "auditorium", z1, s1);
        // signup manager setup
        m1 = new SignupManager();
    }

    @Test(timeout = 50)
    public void test_Cancel1() {
        m1.addAttendee(e1, a1);
        CancelManager c1 = new CancelManager();

        c1.removeAttendee(e1, a1);
        String res1 = e1.toString();
        String exp1 = "1: (topic1) (2017-4-1 9:07) organizer (hugofirst) Attendees () speaker (chrisbacon)";

        assertEquals(exp1, res1);
    }

    @Test(timeout = 50)
    public void test_Cancel2() {
        m1.addAttendee(e1, a1);
        CancelManager c1 = new CancelManager();
        c1.removeAttendee(e1, a1);

        c1.removeAttendee(e1, a1);
        String res1 = e1.toString();
        String exp1 = "1: (topic1) (2017-4-1 9:07) organizer (hugofirst) Attendees () speaker (chrisbacon)";

        assertEquals(exp1, res1);
    }

    @Test(timeout = 50)
    public void test_IsSignedUp() {
        CancelManager c1 = new CancelManager();
        boolean res1 = c1.isSignedUp(e1, a1);

        m1.addAttendee(e1, a1);
        boolean res2 = c1.isSignedUp(e1, a1);

        assertFalse(res1);
        assertTrue(res2);
    }
}
