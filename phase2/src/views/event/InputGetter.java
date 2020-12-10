package views.event;

import controllers.event.EventController;
import enums.EventTypeEnum;
import presenters.InputErrorPresenter;
import presenters.event.EventCreationPresenter;

import java.util.ArrayList;
import java.util.Scanner;

import static enums.EventTypeEnum.PANEL_DISCUSSION;
import static enums.EventTypeEnum.TALK;

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
                if (number <= 0) inputErrorPresenter.positiveNumberPrompt();
                else Input = true;
            }
            catch (NumberFormatException e) { inputErrorPresenter.invalidNumberPrompt(); }
        }
        return number;
    }

    public int getNonNegativeNumber(){
        boolean Input = false;
        int number = 0;
        while (!Input) {
            try {
                number = Integer.parseInt(userInput.nextLine());
                if (number < 0) inputErrorPresenter.nonNegativeNumberPrompt();
                else Input = true;
            }
            catch (NumberFormatException e) { inputErrorPresenter.invalidNumberPrompt(); }
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
            } else { inputErrorPresenter.invalidYesNoPrompt(); }
        }
        return result;
    }


}
