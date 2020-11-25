package presenter;

public interface ConsolePresenter {
    void preUserInput();
    void postUserInput();
    void preUserInput(String input);
    void postUserInput(String input);
}
