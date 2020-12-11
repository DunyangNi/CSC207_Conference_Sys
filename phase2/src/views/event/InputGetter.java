package views.event;

import presenters.InputErrorPresenter;

import java.util.Scanner;

public class InputGetter {
    private final InputErrorPresenter inputErrorPresenter;
    Scanner userInput = new Scanner(System.in);

    public InputGetter(InputErrorPresenter presenter) {
        inputErrorPresenter = presenter;
    }

    public int getPositiveNumber(){
        boolean Input = false;
        int number = 0;
        while (!Input) {
            try {
                number = Integer.parseInt(userInput.nextLine());
                if (number <= 0) inputErrorPresenter.positiveNumberNotification();
                else Input = true;
            }
            catch (NumberFormatException e) { inputErrorPresenter.positiveNumberNotification(); }
        }
        return number;
    }

    public int getNonNegativeNumber(){
        boolean Input = false;
        int number = 0;
        while (!Input) {
            try {
                number = Integer.parseInt(userInput.nextLine());
                if (number < 0) inputErrorPresenter.nonNegativeNumberNotification();
                else Input = true;
            }
            catch (NumberFormatException e) { inputErrorPresenter.nonNegativeNumberNotification(); }
        }
        return number;
    }

    public boolean getBoolean(){
        boolean Input = false;
        boolean result = false;
        while (!Input) {
            String input = userInput.nextLine();
            if (input.equals("Y")) {
                result = true;
                Input = true;
            } else if (input.equals("N")) {
                Input = true;
            } else { inputErrorPresenter.invalidYesNoNotification(); }
        }
        return result;
    }


}
