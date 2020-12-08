package presenters.account;

import presenters.Presenter;

import java.util.Set;

public class AccountPresenter implements Presenter {
    @Override
    public void startPrompt() {
        System.out.println("Logged in}");
        System.out.println();
    }

    public void requestCommandPrompt() {
        System.out.println();
        System.out.println("Enter a command (1-16) or '*' to view the command menu again.");
    }

    public void invalidInputPrompt() {
        System.out.println("{Invalid input, please try again}");
    }

    public void accountList(Set<String> accounts) {
        System.out.println("[USER LIST]");
        System.out.println("===================================");
        for (String acct : accounts) {
            System.out.println(acct);
        }
        System.out.println("===================================");
    }

    public void savedDataPrompt() {
        System.out.println("{Saving data... Saved}");
    }

    @Override
    public void exitPrompt() {
        System.out.println("{Logging out...");
    }
}
