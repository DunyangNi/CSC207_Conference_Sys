package presenter;

public class SpeakerPresenter extends AccountPresenter {
    public void displaySpeakerMenu() {
        System.out.println("[SPEAKER MENU]");
        System.out.println("===================================");
        System.out.println("[ACCOUNT]");
        System.out.println("00 = Logout and exit program");
        System.out.println("0  = Logout");
        System.out.println("1  = View list of all accounts");//Use presenter
        System.out.println("");
        System.out.println("[CONTACTS]");
        System.out.println("2  = add a new contact");//Friend Controller
        System.out.println("3  = remove a contact");
        System.out.println("4  = view contacts list");
        System.out.println("");
        System.out.println("[CONVERSATIONS]");
        System.out.println("5  = message an attendee");//MessageAttendeeController - messageAttendee
        System.out.println("6  = message all attendees for one or multiple talks you're giving");//MessageAttendeeController - messageAttendeesAtTalks
        System.out.println("7  = view your conversation with someone");//ViewConversation - isEmpty, viewMessageFrom
        System.out.println("");
        System.out.println("[EVENTS]");
        System.out.println("8  = see a schedule of talks you're giving");//presenter
        System.out.println("9  = see a schedule of all talks");
        System.out.println("===================================");
    }

    public void invalidInputPrompt() {
        System.out.println("Invalid input, please try again.");
    }

    public void requestCommandPrompt() {
        System.out.println("Enter another command (1-16). Enter '*' to view the command menu again.");
    }
}
