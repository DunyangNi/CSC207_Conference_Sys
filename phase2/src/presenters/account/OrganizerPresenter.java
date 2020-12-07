package presenters.account;

public class OrganizerPresenter extends AccountPresenter {
    public void displayOrganizerMenu() {
        System.out.println("[ORGANIZER MENU]");
        System.out.println("===================================");
        System.out.println("[ACCOUNT]");
        System.out.println("00 = Logout and exit program");
        System.out.println("0  = Logout");
        System.out.println("1  = Register a new speaker account");//AccountCreationController - createSpeaker
        System.out.println("2  = View list of all users");//Use presenters
        System.out.println("");
        System.out.println("[CONTACTS]");
        System.out.println("3  = Add a contact");//same as others
        System.out.println("4  = Remove a contact");
        System.out.println("5  = View list of all contacts");
        System.out.println("");
        System.out.println("[CONVERSATIONS]");
        System.out.println("6  = Message a user"); // TODO Update numbering
        System.out.println("8  = Message all speakers");//MessageSpeakerController - messageAllSpeakers
        System.out.println("9  = Message all attendees");//MessageAttendeeController - messageAllAttendees
        System.out.println("10 = View your conversations");//same as others
        System.out.println("");
        System.out.println("[EVENTS]");
        System.out.println("11 = Register a new event room");//LocationController - addNewLocation
        System.out.println("12 = View list of all rooms");//presenters
        System.out.println("13 = Register a new event");//EventCreationController - createEvent
        System.out.println("14 = Cancel an event");//EventModifyController - cancelEvent
        System.out.println("15 = Reschedule an event");//EventModifyController - rescheduleTalk
        System.out.println("16 = View talk schedule");//use presenters
        System.out.println("===================================");
    }
}
