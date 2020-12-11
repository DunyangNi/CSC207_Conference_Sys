package presenters.account;

public class SpeakerPresenter extends AccountPresenter {
    @Override
    public void displayUserMenu() {
        System.out.println();
        System.out.println("[SPEAKER MENU]");
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
        System.out.println("6  = Message all attendees for one or more of your talks");
        System.out.println("7  = View your conversations");
        System.out.println();
        System.out.println("[EVENTS]");
        System.out.println("8  = View event schedule");
        System.out.println("9  = View your speaker schedule");
        System.out.println("10 = Download event schedule as HTML");
        System.out.println("============================================================");
    }
}
