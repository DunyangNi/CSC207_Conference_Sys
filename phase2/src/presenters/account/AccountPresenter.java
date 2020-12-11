package presenters.account;

import presenters.Presenter;

import java.util.Set;

public abstract class AccountPresenter implements Presenter {
    @Override
    public void startPrompt() {
    }

    public abstract void displayUserMenu();

    public void requestCommandPrompt() {
        System.out.println("Enter a command (1-16) or '*' to view the command menu:");
    }

    public void invalidInputPrompt() {
        System.out.println("{Invalid input, please try again}");
    }

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
