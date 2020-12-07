package presenters.account;

public class AttendeePresenter extends AccountPresenter {
    public void displayAttendeeMenu() {
        System.out.println("[ATTENDEE MENU]");
        System.out.println("===================================");
        System.out.println("[ACCOUNT]");
        System.out.println("00 = Logout and exit program");
        System.out.println("0  = Logout");
        System.out.println("1  = View list of all accounts"); //Use presenters directly
        System.out.println("");
        System.out.println("[CONTACTS]");
        System.out.println("2  = Add a contact");//Friend Controller
        System.out.println("3  = Remove a contact");
        System.out.println("4  = View your contacts");
        System.out.println("");
        System.out.println("[CONVERSATIONS]");
        System.out.println("5  = Message a user"); // TODO Update numbering
        System.out.println("7  = view your conversations");//ViewConversationController-isEmpty, viewMessageFrom
        System.out.println("");
        System.out.println("[EVENTS]");
        System.out.println("8  = View event schedule");//USe OldPresenter
        System.out.println("11 = View your sign-up schedule");//Use OldPresenter
        System.out.println("9  = Sign-up for an event");//SignUpController
        System.out.println("10 = Cancel a sign-up");
        System.out.println("12 = Download all events in HTML");
        System.out.println("===================================");
    }
}
