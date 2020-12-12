package presenters.account;

/**
 * Responsible for displaying organizer user menu
 */
public class OrganizerPresenter extends AccountPresenter {

    /**
     * Display organizer user menu
     */
    @Override
    public void displayUserMenu() {
        System.out.println("\n[ORGANIZER MENU]");
        System.out.println("============================================================");
        System.out.println("[ACCOUNT]");
        System.out.println("00 = Exit");
        System.out.println("0  = Logout");
        System.out.println("1  = Register a new account");
        System.out.println("2  = View list of all users");
        System.out.println();
        System.out.println("[CONTACTS]");
        System.out.println("3  = Add a contact");
        System.out.println("4  = Remove a contact");
        System.out.println("5  = View your contacts");
        System.out.println();
        System.out.println("[CONVERSATIONS]");
        System.out.println("6  = Message a user");
        System.out.println("7  = Message all speakers");
        System.out.println("8  = Message all attendees");
        System.out.println("9  = View your conversations");
        System.out.println();
        System.out.println("[EVENTS]");
        System.out.println("10 = View event schedule");
        System.out.println("11 = View locations directory");
        System.out.println("12 = Add a new location");
        System.out.println("13 = Create a new event");
        System.out.println("14 = Cancel an event");
        System.out.println("15 = Reschedule an event");
        System.out.println("16 = Download event schedule as HTML");
        System.out.println();
        System.out.println("[REQUESTS]");
        System.out.println("17 = View list of pending requests");
        System.out.println("18 = View list of resolved requests");
        System.out.println("19 = Resolve a request");
        System.out.println("============================================================");
    }
}
