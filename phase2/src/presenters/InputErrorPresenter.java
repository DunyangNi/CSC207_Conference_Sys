package presenters;

public interface InputErrorPresenter {
    default void invalidNumberPrompt() {
        System.out.println("Invalid number entered. Ensure the number you entered is positive: ");
    }

    default void invalidYesNoPrompt() {
        System.out.println("Invalid answer entered. Please answer with Y (Yes) or N (No): ");
    }
}
