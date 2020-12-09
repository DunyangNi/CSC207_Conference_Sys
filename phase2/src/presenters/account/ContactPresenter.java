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

    public void UserNotFoundPrompt() { System.out.println("This account does not exist.");}

    public void AlreadyFriendPrompt() { System.out.println("This account is already in your contact list.");}

    public void FriendNotFoundPrompt() { System.out.println("This account does not exist");}

    public void ObjectNotFoundPrompt() { System.out.println("Could not find this account in your contact list.");}

    @Override
    public void exitPrompt() {

    }
}
