package presenters.message;

import java.util.ArrayList;

/**
 * Responsible for displaying contact related functionality prompts and messages
 */
public class ContactPresenter {

    /**
     * Header of prompt for adding a contact
     */
    public void addContactHeader() {
        System.out.println("\n[ADD A CONTACT]");
    }

    /**
     * Body of prompt for adding a contact
     */
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


    /**
     * Header of prompt for removing a contact
     */
    public void removeContactHeader() { System.out.println("\n[REMOVE A CONTACT]"); }

    /**
     * Body of prompt for removing a contact
     */
    public void removeContactPrompt() {
        System.out.println("Enter the username of a contact to remove: ");
    }

    public void removeContactFailureNotification() {
        System.out.println("{Removing a contact cancelled.}");
    }

    public void removeContactSuccessNotification(String contact) {
        System.out.println("{Successfully removed " + contact + " from your contacts!}");
    }


    /**
     * Display contact list given the contacts
     * @param contacts List of usernames of contacts
     */
    public void displayContactList(ArrayList<String> contacts) {
        System.out.println("\n[MY CONTACTS]");
        System.out.println("============================================================");
        if (contacts.isEmpty()) {
            System.out.println("{No contacts}");
        } else for (String contact : contacts) {
            System.out.println(contact);
        }
        System.out.println("============================================================");
    }
}
