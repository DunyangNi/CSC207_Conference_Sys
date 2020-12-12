package presenters.account;

import presenters.Presenter;

import java.util.Set;

/**
 * Responsible for displaying user menus and account list
 */

public abstract class AccountPresenter implements Presenter {
    @Override
    public void startPrompt() {
    }

    /**
     * Display user menu
     */
    public abstract void displayUserMenu();

    /**
     * Displays request for user to enter a command to continue
     */
    public void requestCommandPrompt() {
        System.out.println("Enter a command (1-16) or '*' to view the command menu:");
    }


    public void invalidInputPrompt() {
        System.out.println("{Invalid input, please try again}");
    }

    /**
     * Display account list
     * @param accounts List of account usernames to display
     */
    public void displayAccountList(Set<String> accounts) {
        System.out.println("[USER LIST]");
        System.out.println("============================================================");
        for (String acct : accounts) {
            System.out.println(acct);
        }
        System.out.println("============================================================");
    }

    @Override
    public void exitPrompt() {
        System.out.println("{Logging out... Logged out}");
        System.out.println();
    }
}
