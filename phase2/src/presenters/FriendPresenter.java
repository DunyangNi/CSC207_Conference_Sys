package presenters;

public class FriendPresenter implements ConsolePresenter {
    @Override
    public void startPrompt() {
    }

    public void addContactPrompt() {
        System.out.println("Please enter the username of a contact to add: ");
    }

    public void removeContactPrompt() {
        System.out.println("Please enter the username of a contact to remove: ");
    }

    public void contactListPrompt() {
        System.out.println("[CONTACTS]");
    }

    public void emptyContactListPrompt() {
        System.out.println("Your contact list is empty.");
    }

    public void friendPrompt(String username) {
        System.out.println(username);
    }

    @Override
    public void exitPrompt() {

    }
}
