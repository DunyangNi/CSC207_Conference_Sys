import java.util.ArrayList;
import java.util.Calendar;

/**
 * SignupManager adds given Attendee to a given EventTalk.
 *
 * <pre>
 * Use Case SignupManager
 * Responsibilities:
 *      Stores a given EventTalk
 *      Can add given Attendee to EventTalk
 *
 * Collaborators:
 *      Attendee, EventTalk
 *
 * Representation Invariants:
 *      Given Attendee is not already signed into EventTalk
 *      EventTalk has enough seats for a given Attendee
 * </pre>
 */
public class SignupManager {
    public EventTalk talk;

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------

    /**
     * Create a SignupManager with a given EventTalk.
     *
     * @param talk given EventTalk
     */
    public SignupManager(EventTalk talk) {
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
     * Adds given Attendee to Talk.
     *
     * @param attendee given Attendee
     */
    public void addAttendee(Attendee attendee) {
        // Get list of Attendees from EventTalk and list of EventTalks from Attendee
        ArrayList<Account> eventAttendees = talk.getAttendees();
        ArrayList<EventTalk> attendeeEvents = attendee.getAttendeeTalks();
        // Modify each list
        eventAttendees.add(attendee);
        attendeeEvents.add(talk);
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
        Speaker s1 = new Speaker("chrisbacon", "pass000", "Chris", "Bacon");
        Attendee a1 = new Attendee("johndoe", "pass123", "John", "Doe");
        Attendee a2 = new Attendee("janedoe", "pass456", "Jane", "Doe");
        EventTalk e1 = new EventTalk("topic1", evt_date, "auditorium", z1, s1);

        // Create a SignupManager and add Attendee
        SignupManager m1 = new SignupManager(e1);
        m1.addAttendee(a1);
        m1.addAttendee(a2);
        System.out.println(e1);
    }
}
