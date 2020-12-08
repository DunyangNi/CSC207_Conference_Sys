package presenters.account;

public class SpeakerPresenter extends AccountPresenter {
    public void displaySpeakerMenu() {
        // TODO Update numbering
        System.out.println("[SPEAKER MENU]");
        System.out.println("===================================");
        System.out.println("[ACCOUNT]");
        System.out.println("00 = Logout and exit");
        System.out.println("0  = Logout");
        System.out.println("1  = View list of all users");//Use presenters
        System.out.println();
        System.out.println("[CONTACTS]");
        System.out.println("2  = Add a contact");//Friend Controller
        System.out.println("3  = Remove a contact");
        System.out.println("4  = View your contacts");
        System.out.println();
        System.out.println("[CONVERSATIONS]");
        System.out.println("5  = Message a user");
        System.out.println("6  = Message all attendees for one or more of your talks");//MessageAttendeeController - messageTalkAttendees
        System.out.println("7  = View your conversations");//ViewConversation - isEmpty, viewMessageFrom
        System.out.println();
        System.out.println("[EVENTS]");
        System.out.println("9  = View event schedule");
        System.out.println("8  = View your speaker schedule");//presenters
        System.out.println("===================================");
    }
}
