package presenter;

public class RegistrationPresenter implements ConsolePresenter {
    @Override
    public void preUserInput() {
        System.out.println("[REGISTRATION MENU]");
        System.out.println("1 = Register an Attendee account:");
        System.out.println("2 = Register an Organizer account:");
    }

    public void invalidInputPrompt() {
        System.out.println("Invalid input, please try again:");
    }

    public void organizerCodePrompt() {
        System.out.println("Enter the Organizer account registration code:");
    }

    public void invalidCodePrompt() {
        System.out.println("Incorrect code, please try again:");
    }
    public void usernamePrompt() {
        System.out.println("Enter a username:");
    }

    public void takenUsernamePrompt() {
        System.out.println("This username is already taken, please try again:");
    }

    public void passwordPrompt() {
        System.out.println("Enter a password:");
    }

    @Override
    public void postUserInput() {
        System.out.println("Registered...");
    }
}
