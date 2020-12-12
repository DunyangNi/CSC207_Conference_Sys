package presenters.account;

import enums.AccountTypeEnum;

/**
 * Responsible for displaying registration functionality prompts and messages
 */
public class RegistrationPresenter {

    /**
     * Header of registration action
     */
    public void registrationHeader() { System.out.println("\n[REGISTRATION MENU]");}

    /**
     * Display registration menu
     */
    public void registrationMenu() {
        System.out.println("============================================================");
        System.out.println("1 = Register an Attendee account");
        System.out.println("2 = Register an Speaker account");
        System.out.println("3 = Register an Organizer account");
        System.out.println("4 = Register a VIP Attendee account");
        System.out.println("============================================================");
    }

    public void invalidCommandNotification() {
        System.out.println("{Invalid input, please try again.}");
    }

    /**
     * Asks for registration code (if applicable) based on account type
     * @param accountTypeEnum an enum representing an account type
     */
    public void registrationCodePrompt(AccountTypeEnum accountTypeEnum) {
        switch (accountTypeEnum) {
            case SPEAKER:
                System.out.println("Please enter the Speaker registration code:");
                break;
            case ORGANIZER:
                System.out.println("Please enter the Organizer registration code:");
                break;
            case VIP_ATTENDEE:
                System.out.println("Please enter the Vip Attendee registration code:");
                break;
            default:
                break;
        }
    }

    public void invalidCodeNotification() {
        System.out.println("{Incorrect code, please try again.}");
    }

    /**
     * Asks for username
     */

    public void usernamePrompt() {
        System.out.println("Enter a username:");
    }

    public void takenUsernameNotification() {
        System.out.println("{This username is taken, please try again.}");
    }

    /**
     * Asks for password
     */

    public void passwordPrompt() {
        System.out.println("Enter a password:");
    }

    public void accountAlreadyExistsNotification() {
        System.out.println("{This account already exists.}");
    }

    public void registrationSuccessNotification() {
        System.out.println("{Registering... Registered}");
    }
}
