package views.event;

import controllers.event.LocationController;
import enums.ViewEnum;
import presenters.event.LocationPresenter;
import views.View;

import java.util.Scanner;

public class LocationAddView implements View {
    private final LocationPresenter locationPresenter;
    private final LocationController locationController;
    private final Scanner userInput = new Scanner(System.in);

    public LocationAddView(LocationController locationController, LocationPresenter locationPresenter) {
        this.locationController = locationController;
        this.locationPresenter = locationPresenter;
    }

    public ViewEnum runView() {
        locationPresenter.startPrompt();

        boolean nameChosen = false;
        String name = "";
        locationPresenter.namePrompt();
        while (!nameChosen) {
            name = userInput.nextLine();
            if (locationController.isNewLocation(name)) nameChosen = true;
            else locationPresenter.nameTakenPrompt();
        }

        boolean capacityInput = false;
        int capacity = 0;
        locationPresenter.capacityPrompt();
        while (!capacityInput) {
            try {
                capacity = Integer.parseInt(userInput.nextLine());
                if (capacity <= 0) locationPresenter.invalidNumberPrompt();
                else capacityInput = true;
            }
            catch (NumberFormatException e) { locationPresenter.invalidNumberPrompt(); }
        }

        boolean tablesInput = false;
        int tables = 0;
        locationPresenter.tablesPrompt();
        while (!tablesInput) {
            try {
                tables = Integer.parseInt(userInput.nextLine());
                if (tables < 0) locationPresenter.invalidNumberPrompt();
                else tablesInput = true;
            }
            catch (NumberFormatException e) { locationPresenter.invalidNumberPrompt(); }
        }

        boolean chairsInput = false;
        int chairs = 0;
        locationPresenter.chairsPrompt();
        while (!chairsInput) {
            try {
                chairs = Integer.parseInt(userInput.nextLine());
                if (chairs < 0) locationPresenter.invalidNumberPrompt();
                else chairsInput = true;
            }
            catch (NumberFormatException e) { locationPresenter.invalidNumberPrompt(); }
        }

        boolean internetInput = false;
        boolean hasInternet = false;
        locationPresenter.internetPrompt();
        while (!internetInput) {
            String input = userInput.nextLine();
            if (input.equals("Y")) {
                hasInternet = true;
                internetInput = true;
            } else if (input.equals("N")) {
                internetInput = true;
            } else { locationPresenter.invalidYesNoPrompt(); }
        }

        boolean soundSystemInput = false;
        boolean hasSoundSystem = false;
        locationPresenter.soundSystemPrompt();
        while (!soundSystemInput) {
            String input = userInput.nextLine();
            if (input.equals("Y")) {
                hasSoundSystem = true;
                soundSystemInput = true;
            } else if (input.equals("N")) {
                soundSystemInput = true;
            } else { locationPresenter.invalidYesNoPrompt(); }
        }

        boolean presentationScreenInput = false;
        boolean hasPresentationScreen = false;
        locationPresenter.presentationScreenPrompt();
        while (!presentationScreenInput) {
            String input = userInput.nextLine();
            if (input.equals("Y")) {
                hasPresentationScreen = true;
                presentationScreenInput = true;
            } else if (input.equals("N")) {
                presentationScreenInput = true;
            } else { locationPresenter.invalidYesNoPrompt(); }
        }

        locationPresenter.furtherNotesPrompt();
        String furtherNotes = userInput.nextLine();

        locationController.addNewLocation(name, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, furtherNotes);
        locationPresenter.exitPrompt();
        return ViewEnum.VOID;
    }
}

