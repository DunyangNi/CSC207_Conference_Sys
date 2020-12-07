package presenters.account;

public class SpeakerPresenter extends AccountPresenter {
    public void displaySpeakerMenu() {
        System.out.println("[SPEAKER MENU]");
        System.out.println("===================================");
        System.out.println("[ACCOUNT]");
        System.out.println("00 = Logout and exit program");
        System.out.println("0  = Logout");
        System.out.println("1  = View list of all accounts");//Use presenters
        System.out.println("");
        System.out.println("[CONTACTS]");
        System.out.println("2  = add a new contact");//Friend Controller
        System.out.println("3  = remove a contact");
        System.out.println("4  = view contacts list");
        System.out.println("");
        System.out.println("[CONVERSATIONS]");
        System.out.println("5  = Message a user");
        System.out.println("6  = message all attendees for one or multiple talks you're giving");//MessageAttendeeController - messageTalkAttendees
        System.out.println("7  = view your conversation with someone");//ViewConversation - isEmpty, viewMessageFrom
        System.out.println("");
        System.out.println("[EVENTS]");
        System.out.println("8  = see a schedule of talks you're giving");//presenters
        System.out.println("9  = see a schedule of all talks");
        System.out.println("===================================");
    }
}
