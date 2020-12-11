package presenters.account;

import presenters.Presenter;

import java.util.ArrayList;

public class ContactPresenter implements Presenter {
    @Override
    public void startPrompt() {
    }

    //Add
    public void addContactHeader() {
        System.out.println();
        System.out.println("[ADD A CONTACT]");
    }

    public void addContactPrompt() {
        System.out.println("Enter the username of a contact to add: ");
    }

    public void accountNotFoundNotification() {
        System.out.println("{Sorry, this account could not be found.}");
    }

    public void contactNotFoundNotification() {
        System.out.println("{Sorry, this account is not in your contact list.}");
    }

    public void alreadyContactNotification() {
        System.out.println("{This account is already in your contact list.}");
    }

    public void addContactFailureNotification() {
        System.out.println("{Adding a contact cancelled.}");
    }

    public void addContactSuccessNotification(String contact) {
        System.out.println("{Successfully added " + contact + " to your contacts!}");
    }


    //Remove
    public void removeContactHeader() {
        System.out.println();
        System.out.println("[REMOVE A CONTACT]");
    }

    public void removeContactPrompt() {
        System.out.println("Enter the username of a contact to remove: ");
    }

    public void removeContactFailureNotification() {
        System.out.println("{Removing a contact cancelled.}");
    }

    public void removeContactSuccessNotification(String contact) {
        System.out.println("{Successfully removed " + contact + " from your contacts!}");
    }


    //List
    public void displayContactList(ArrayList<String> contacts) {
        System.out.println();
        System.out.println("[MY CONTACTS]");
        System.out.println("============================================================");
        if (contacts.isEmpty()) {
            System.out.println("{No contacts}");
        } else for (String contact : contacts) {
            System.out.println(contact);
        }
        System.out.println("============================================================");
    }

    @Override
    public void exitPrompt() {
    }
}
