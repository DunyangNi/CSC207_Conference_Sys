package presenter;

public class OrganizerPresenter implements ConsolePresenter {
    @Override
    public void startPrompt() {
        System.out.println("Logged in.");
    }

    public void organizerMenuPrompt() {
        System.out.println("[ORGANIZER MENU]");
        System.out.println("===================================");
        System.out.println("[ACCOUNT]");
        System.out.println("00 = Logout and exit program");
        System.out.println("0  = Logout");
        System.out.println("1  = Register a new speaker account");
        System.out.println("2  = View list of all accounts");
        System.out.println("");
        System.out.println("[CONTACTS]");
        System.out.println("3  = Add a contact");
        System.out.println("4  = Remove a contact");
        System.out.println("5  = View list of all contacts");
        System.out.println("");
        System.out.println("[CONVERSATIONS]");
        System.out.println("6  = Message a speaker");
        System.out.println("7  = Message an attendee");
        System.out.println("8  = Message all speakers");
        System.out.println("9  = Message all attendees");
        System.out.println("10 = View your conversations");
        System.out.println("");
        System.out.println("[EVENTS]");
        System.out.println("11 = Register a new event room");
        System.out.println("12 = View list of all rooms");
        System.out.println("13 = Register a new event");
        System.out.println("14 = Cancel an event");
        System.out.println("15 = Reschedule an event");
        System.out.println("16 = View talk schedule");
        System.out.println("===================================");
    }

    @Override
    public void exitPrompt() {
        System.out.println("Logging out...");
    }
}
