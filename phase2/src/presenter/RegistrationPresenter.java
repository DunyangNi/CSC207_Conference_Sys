package presenter;

public class RegistrationPresenter implements ConsolePresenter {
    @Override
    public void preUserInput() {
    }

    @Override
    public void postUserInput() {
    }

    public void preUserInput(String input) {
        switch (input) {
            case "accountType":
                System.out.println("Enter '1' to register an Attendee account.\nEnter '2' to register an Organizer account.");
                break;
            case "code":
                System.out.println("Enter the Organizer account registration code:");
                break;
            case "username":
                System.out.println("Enter a username:");
                break;
            case "password":
                System.out.println("Enter a password:");
                break;
        }
    }

    public void postUserInput(String input) {
        switch (input) {
            case "accountType":
                System.out.println("Invalid input, please try again.");
                break;
            case "code":
                System.out.println("Incorrect code, please try again.");
            case "username":
                System.out.println("This username is already taken, please try again:");
                break;
        }
    }
}
