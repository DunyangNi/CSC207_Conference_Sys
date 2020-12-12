package views.event;

import controllers.event.LocationController;
import enums.ViewEnum;
import presenters.event.LocationPresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for adding locations
 *
 * Fields:
 * locationPresenter: LocationPresenter responsible for displaying relevant prompts
 * locationController: LocationController responsible for location functionality
 * getInputView: GetInputView responsible for getting input
 */

public class LocationAddView implements View {
    private final LocationPresenter locationPresenter;
    private final LocationController locationController;
    private final GetInputView getInputView;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>LocationAddView</code> based on the following parameters
     * @param locationController The given LocationController
     * @param locationPresenter The given LocationPresenter
     */

    public LocationAddView(LocationController locationController, LocationPresenter locationPresenter) {
        this.locationController = locationController;
        this.locationPresenter = locationPresenter;
        getInputView = new GetInputView(locationPresenter);
    }

    /**
     * Run the view.
     * @return ViewEnum.VOID
     */

    public ViewEnum runView() {
        locationPresenter.locationCreationHeader();

        boolean nameChosen = false;
        String name = "";
        locationPresenter.namePrompt();
        while (!nameChosen) {
            name = userInput.nextLine();
            if (locationController.isNewLocation(name)) nameChosen = true;
            else locationPresenter.nameTakenNotification();
        }


        locationPresenter.capacityPrompt();
        int capacity = getInputView.getPositiveNumber();


        locationPresenter.tablesPrompt();
        int tables = getInputView.getNonNegativeNumber();


        locationPresenter.chairsPrompt();
        int chairs = getInputView.getNonNegativeNumber();


        locationPresenter.internetPrompt();
        boolean hasInternet = getInputView.getBoolean();


        locationPresenter.soundSystemPrompt();
        boolean hasSoundSystem = getInputView.getBoolean();


        locationPresenter.presentationScreenPrompt();
        boolean hasPresentationScreen = getInputView.getBoolean();

        locationPresenter.furtherNotesPrompt();
        String furtherNotes = userInput.nextLine();

        locationController.addNewLocation(name, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, furtherNotes);
        locationPresenter.locationCreationSuccessNotification();
        return ViewEnum.VOID;
    }
}

