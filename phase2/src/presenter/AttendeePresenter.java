package presenter;

public class AttendeePresenter implements ConsolePresenter {
    @Override
    public void startPrompt() {
        System.out.println("Logged in)");
    }

    public void attendeeMenuPrompt() {
        System.out.println("[ATTENDEE MENU]");
        System.out.println("===================================");
        System.out.println("[ACCOUNT]");
        System.out.println("00 = Logout and exit program");
        System.out.println("0  = Logout");
        System.out.println("1  = View list of all accounts"); //Use presenter directly
        System.out.println("");
        System.out.println("[CONTACTS]");
        System.out.println("2  = add a new contact"); //FriendController
        System.out.println("3  = remove a contact");
        System.out.println("4  = see contacts list");
        System.out.println("");
        System.out.println("[CONVERSATIONS]");
        System.out.println("5  = message another attendee"); //MessageAttendeeController-messageAttendee
        System.out.println("6  = message a speaker"); //MessageSpeakerController-messageSpeaker
        System.out.println("7  = view your conversation with someone");//ViewConversationController-isEmpty, viewMessageFrom
        System.out.println("");
        System.out.println("[EVENTS]");
        System.out.println("8  = see talk schedule");//USe Presenter
        System.out.println("9  = sign up for a talk");//SignUpController
        System.out.println("10  = cancel enrolment for a talk");
        System.out.println("11 = see a schedule of talks you're attending");//Use Presenter
        System.out.println("===================================");
    }

    @Override
    public void exitPrompt() {
        System.out.println("Logging out...");
    }
}
