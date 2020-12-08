package presenters.account;

import presenters.Presenter;

import java.util.ArrayList;

public class ContactPresenter implements Presenter {
    @Override
    public void startPrompt() {
    }

    public void addContactPrompt() {
        System.out.println("Please enter the username of a contact to add:");
    }

    public void removeContactPrompt() {
        System.out.println("Please enter the username of a contact to remove:");
    }

    public void contactListPrompt(ArrayList<String> friendList) {
        System.out.println("[CONTACTS]");
        System.out.println("===================================");
        if (friendList.isEmpty()) {
            System.out.println("{Empty}");
        } else {
            for (String friend : friendList) {
                System.out.println(friend);
            }
        }
        System.out.println("===================================");
    }

    @Override
    public void exitPrompt() {

    }
}
