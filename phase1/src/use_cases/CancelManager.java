package use_cases;

import java.util.ArrayList;
import java.util.Calendar;
import entities.EventTalk;
import entities.Account;
import entities.Attendee;
import entities.Organizer;
import entities.Speaker;

/**
 * CancelManager removes given Attendee from given EventTalk.
 *
 * <pre>
 * Use Case CancelManager
 * Responsibilities:
 *      Stores a given EventTalk
 *      Can remove given Attendee to given EventTalk
 *
 * Collaborators:
 *      Attendee, EventTalk
 *
 * Representation Invariants:
 *      Given Attendee is currently signed in to given EventTalk
 * </pre>
 */
public class CancelManager {
    public EventTalk talk;

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------

    /**
     * Create a CancelManager with a given EventTalk.
     *
     * @param talk given EventTalk
     */
    public CancelManager(EventTalk talk) {
        this.talk = talk;
    }

    //------------------------------------------------------------
    // Getters
    //------------------------------------------------------------

    /**
     * Gets EventTalk that CancelManager is managing.
     *
     * @return given EventTalk
     */
    public EventTalk getTalk() { return this.talk; }

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Removes given Attendee from Talk.
     *
     * @param attendee given Attendee
     */
    public void removeAttendee(Attendee attendee) {
        // Get list of Attendees from EventTalk and list of EventTalks from Attendee
        ArrayList<Account> eventAttendees = talk.getAttendees();
        ArrayList<EventTalk> attendeeEvents = attendee.getAttendeeTalks();
        // Modify each list
        eventAttendees.remove(attendee);
        attendeeEvents.remove(talk);
        // Set new list of Attendees to EventTalk and new list of EventTalks to Attendee
        attendee.setAttendeeTalks(attendeeEvents);
        talk.setAttendees(eventAttendees);
    }

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
        SignupManager m1 = new SignupManager(e1);
        m1.addAttendee(a1);
        System.out.println(e1);

        // Create a CancelManager and remove Attendee
        CancelManager c1 = new CancelManager(e1);
        c1.removeAttendee(a1);
        System.out.println(e1);
    }
}
