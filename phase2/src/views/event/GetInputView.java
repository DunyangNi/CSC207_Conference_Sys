package views.event;

import presenters.interfaces.InputErrorPresenter;

import java.util.Scanner;

/**
 * View responsible for getting input of: integers of various bounds, booleans to yes/no questions.
 */
public class GetInputView {
    private final InputErrorPresenter inputErrorPresenter;
    Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>GetInputView</code> based on the following parameters
     *
     * @param presenter The given InputErrorPresenter
     */
    public GetInputView(InputErrorPresenter presenter) {
        inputErrorPresenter = presenter;
    }

    /**
     * Attempt to get the positive number in the input, and displays a error notification if it fails to do so.
     *
     * @return positive number in userInput, if it exists. Otherwise return 0
     */
    public int getPositiveNumber() {
        boolean Input = false;
        int number = 0;
        while (!Input) {
            try {
                number = Integer.parseInt(userInput.nextLine());
                if (number <= 0) inputErrorPresenter.positiveNumberNotification();
                else Input = true;
            } catch (NumberFormatException e) {
                inputErrorPresenter.positiveNumberNotification();
            }
        }
        return number;
    }

    /**
     * Attempt to get the non-negative number in the input, and displays a error notification if it fails to do so.
     *
     * @return non-negative number in userInput, if it exists. Otherwise return 0
     */
    public int getNonNegativeNumber() {
        boolean Input = false;
        int number = 0;
        while (!Input) {
            try {
                number = Integer.parseInt(userInput.nextLine());
                if (number < 0) inputErrorPresenter.nonNegativeNumberNotification();
                else Input = true;
            } catch (NumberFormatException e) {
                inputErrorPresenter.nonNegativeNumberNotification();
            }
        }
        return number;
    }

    /**
     * Attempt to get the boolean in the input, and displays a error notification if it fails to do so.
     *
     * @return boolean in userInput, if it exists. Otherwise return false
     */
    public boolean getBoolean() {
        boolean Input = false;
        boolean result = false;
        while (!Input) {
            String input = userInput.nextLine();
            if (input.equals("Y")) {
                result = true;
                Input = true;
            } else if (input.equals("N")) {
                Input = true;
            } else {
                inputErrorPresenter.invalidYesNoNotification();
            }
        }
        return result;
    }
}
