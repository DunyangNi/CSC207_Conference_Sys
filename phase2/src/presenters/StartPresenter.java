package presenters;

public class StartPresenter implements Presenter {
    @Override
    public void startPrompt() {
        System.out.println("[START MENU]");
        System.out.println("0 = Exit program:");
        System.out.println("1 = Login to your account:");
        System.out.println("2 = Register a new account:");
    }

    public void invalidCommandNotification() {
        System.out.println("{Invalid input, please try again.}");
    }

    @Override
    public void exitPrompt() {
    }
}
