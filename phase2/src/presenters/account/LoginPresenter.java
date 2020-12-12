package presenters.account;

/**
 * Responsible for displaying login related functionality prompts and messages
 */

public class LoginPresenter {

    /**
     * Header of login action
     */
    public void loginHeader() { System.out.println("\n[LOGIN MENU]"); }

    /**
     * Asks for username
     */
    public void usernamePrompt() {
        System.out.println("Please enter your username:");
    }

    public void usernameNotFoundNotification() {
        System.out.println("{This username does not exist, please try again.}");
    }

    /**
     * Asks for password
     */
    public void passwordPrompt() {
        System.out.println("Please enter your password:");
    }

    public void incorrectPasswordNotification() {
        System.out.println("{Incorrect password, please try again.}");
    }

    public void failedLoginPrompt() {
        System.out.println("Try again? (Y/N)");
    }

    public void loginFailureNotification() {
        System.out.println("{Logging in... Logged in}");
    }
}
