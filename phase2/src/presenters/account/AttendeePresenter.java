package presenters.account;

/**
 * Responsible for displaying attendee user menu
 */

public class AttendeePresenter extends AccountPresenter {

    /**
     * Display attendee user menu
     */
    @Override
    public void displayUserMenu() {
        System.out.println("\n[ATTENDEE MENU]");
        System.out.println("============================================================");
        System.out.println("[ACCOUNT]");
        System.out.println("00 = Exit");
        System.out.println("0  = Logout");
        System.out.println("1  = View list of all users");
        System.out.println();
        System.out.println("[CONTACTS]");
        System.out.println("2  = Add a contact");
        System.out.println("3  = Remove a contact");
        System.out.println("4  = View your contacts");
        System.out.println();
        System.out.println("[CONVERSATIONS]");
        System.out.println("5  = Message a user");
        System.out.println("6  = View your conversations");
        System.out.println();
        System.out.println("[EVENTS]");
        System.out.println("7  = View event schedule");
        System.out.println("8  = Download event schedule as HTML");
        System.out.println("9  = View your sign-up schedule");
        System.out.println("10 = Sign-up for an event");
        System.out.println("11 = Cancel a sign-up");
        System.out.println();
        System.out.println("[REQUESTS]");
        System.out.println("12 = Create a request");
        System.out.println("============================================================");
    }
}
