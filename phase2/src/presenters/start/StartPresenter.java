package presenters.start;

public class StartPresenter {
    public void startHeader() { System.out.println("\n[START MENU]"); }

    public void startMenu() {
        System.out.println("============================================================");
        System.out.println("0 = Exit program");
        System.out.println("1 = Login to your account");
        System.out.println("2 = Register a new account");
        System.out.println("============================================================");
    }

    public void invalidCommandNotification() { System.out.println("{Invalid input, please try again.}"); }
}
