package presenter;

public class MessagePresenter implements ConsolePresenter {
    @Override
    public void startPrompt() {
    }

    public void usernamePrompt() {
        System.out.println("Please enter the username of the user you wish to message:");
    }

    public void messagePrompt() {
        System.out.println("Please enter the message you want to send:");
    }

    @Override
    public void exitPrompt() {
    }
}
