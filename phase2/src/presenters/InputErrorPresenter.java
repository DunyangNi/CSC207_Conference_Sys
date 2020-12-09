package presenters;

public interface InputErrorPresenter {
    default void invalidNumberPrompt() {
        System.out.println("Invalid number entered. Ensure your input is a number: ");
    }

    default void nonNegativeNumberPrompt() {
        System.out.println("Invalid number entered. Ensure your input is a non-negative number: ");
    }

    default void positiveNumberPrompt() {
        System.out.println("Invalid number entered. Ensure your input is a positive number: ");
    }

    default void invalidYesNoPrompt() {
        System.out.println("Invalid answer entered. Please answer with Y (Yes) or N (No): ");
    }
}
