package presenters.interfaces;

public interface InputErrorPresenter {
    default void invalidNumberNotification() {
        System.out.println("{Invalid number entered. Ensure your input is a number.}");
    }

    default void nonNegativeNumberNotification() {
        System.out.println("{Invalid number entered. Ensure your input is a non-negative number.}");
    }

    default void positiveNumberNotification() {
        System.out.println("{Invalid number entered. Ensure your input is a positive number.}");
    }

    default void invalidYesNoNotification() {
        System.out.println("{Invalid answer entered. Please answer with Y (Yes) or N (No).}");
    }

    default void invalidCommandNotification() {
        System.out.println("{Invalid command entered. Ensure the input is a valid option.}");
    }
}
