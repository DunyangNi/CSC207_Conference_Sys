package presenters.account;

import enums.AccountTypeEnum;
import presenters.Presenter;

public class RegistrationPresenter implements Presenter {
    @Override
    public void startPrompt() {
        System.out.println("[REGISTRATION MENU]");
        System.out.println("1 = Register an Attendee account:");
        System.out.println("2 = Register an Speaker account:");
        System.out.println("3 = Register an Organizer account:");
        System.out.println("4 = Register a Vip Attendee account:");
    }

    public void invalidCommandNotification() {
        System.out.println("{Invalid input, please try again.}");
    }

    public void displayCodePrompt(AccountTypeEnum accountTypeEnum) {
        switch (accountTypeEnum) {
            case SPEAKER:
                System.out.println("Enter the Speaker registration code:");
                break;
            case ORGANIZER:
                System.out.println("Enter the Organizer registration code:");
                break;
            case VIP_ATTENDEE:
                System.out.println("Enter the Vip Attendee registration code:");
                break;
            default:
                break;
        }
    }

    public void invalidCodeNotification() {
        System.out.println("{Incorrect code, please try again.}");
    }

    public void usernamePrompt() {
        System.out.println("Enter a username:");
    }

    public void usernameIsTakenNotification() {
        System.out.println("{This username is already taken, please try again.}");
    }

    public void passwordPrompt() {
        System.out.println("Enter a password:");
    }

    public void accountAlreadyExistsNotification() {
        System.out.println("{This account already exists.}");
    }

    @Override
    public void exitPrompt() {
        System.out.println("{Registering... Registered}");
    }
}
