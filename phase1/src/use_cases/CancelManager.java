package use_cases;
import java.util.ArrayList;
import java.util.Calendar;
import entities.*;

/**
 * CancelManager removes given Attendee from given EventTalk.
 *
 * <pre>
 * Use Case CancelManager
 * Responsibilities:
 *      Can remove given Attendee to given EventTalk
 *      Can check whether given Attendee is already in given EventTalk or not
 *
 * Collaborators:
 *      Attendee, EventTalk
 * </pre>
 */
public class CancelManager {
    public EventTalk talk;

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Removes given Attendee from Talk.
     * Does nothing if Attendee is not in Talk.
     * @param attendee given Attendee
     */
    public void removeAttendee(EventTalk talk, Attendee attendee) {
        if (isSignedUp(talk, attendee)) {
            // Get and copy list of Attendees from EventTalk and list of EventTalks from Attendee
            ArrayList<Account> eventAttendees = new ArrayList<>(talk.getAttendees());
            ArrayList<EventTalk> attendeeEvents = new ArrayList<>(attendee.getAttendeeTalks());
            // Modify each list
            eventAttendees.remove(attendee);
            attendeeEvents.remove(talk);
            // Set new list of Attendees to EventTalk and new list of EventTalks to Attendee
            attendee.setAttendeeTalks(attendeeEvents);
            talk.setAttendees(eventAttendees);
        }
    }

    /**
     * Returns whether given EventTalk contains a given Attendee.
     * @param talk given EventTalk
     * @param attendee given Attendee
     * @return whether talk contains Attendee or not
     */
    public boolean isSignedUp(EventTalk talk, Attendee attendee) { return talk.getAttendees().contains(attendee); }

    //------------------------------------------------------------
    // Test
    //------------------------------------------------------------

    public static void main(String[] args){
        // Create an event, organizer, speaker, attendee
        Calendar evt_date = Calendar.getInstance();
        evt_date.set(2020, Calendar.APRIL, 23, 10, 12);
        Organizer z1 = new Organizer("hugofirst", "pass101", "Hugo", "First");
        Speaker s1 = new Speaker("kendoe", "pass789", "Ken", "Doe");
        Attendee a1 = new Attendee("johndoe", "pass123", "John", "Doe");
        EventTalk e1 = new EventTalk("topic1", evt_date, "auditorium", z1, s1);

        // Create a SignupManager and add Attendee
        SignupManager m1 = new SignupManager();
        m1.addAttendee(e1, a1);
        System.out.println(e1);

        // Create a CancelManager and remove Attendee
        CancelManager c1 = new CancelManager();
        c1.removeAttendee(e1, a1);
        System.out.println(e1);
        c1.removeAttendee(e1, a1);
        System.out.println(e1);
    }
}
