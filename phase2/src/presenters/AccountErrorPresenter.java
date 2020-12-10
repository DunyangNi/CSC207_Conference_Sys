package presenters;

public interface AccountErrorPresenter {
    default void accountNotFoundPrompt() { System.out.println("Sorry, this account could not be found."); }
}
