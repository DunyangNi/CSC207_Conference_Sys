package views.event;

import controllers.event.LocationController;
import enums.ViewEnum;
import presenters.event.LocationPresenter;
import views.View;

import java.util.Scanner;

public class LocationAddView implements View {
    private final LocationPresenter locationPresenter;
    private final LocationController locationController;
    private final InputGetter inputGetter;
    private final Scanner userInput = new Scanner(System.in);

    public LocationAddView(LocationController locationController, LocationPresenter locationPresenter) {
        this.locationController = locationController;
        this.locationPresenter = locationPresenter;
        inputGetter = new InputGetter(locationPresenter);
    }

    public ViewEnum runView() {
        locationPresenter.startPrompt();

        boolean nameChosen = false;
        String name = "";
        locationPresenter.namePrompt();
        while (!nameChosen) {
            name = userInput.nextLine();
            if (locationController.isNewLocation(name)) nameChosen = true;
            else locationPresenter.nameTakenNotification();
        }


        locationPresenter.capacityPrompt();
        int capacity = inputGetter.getPositiveNumber();


        locationPresenter.tablesPrompt();
        int tables = inputGetter.getNonNegativeNumber();


        locationPresenter.chairsPrompt();
        int chairs = inputGetter.getNonNegativeNumber();


        locationPresenter.internetPrompt();
        boolean hasInternet = inputGetter.getBoolean();


        locationPresenter.soundSystemPrompt();
        boolean hasSoundSystem = inputGetter.getBoolean();


        locationPresenter.presentationScreenPrompt();
        boolean hasPresentationScreen = inputGetter.getBoolean();

        locationPresenter.furtherNotesPrompt();
        String furtherNotes = userInput.nextLine();

        locationController.addNewLocation(name, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, furtherNotes);
        locationPresenter.exitPrompt();
        return ViewEnum.VOID;
    }
}

