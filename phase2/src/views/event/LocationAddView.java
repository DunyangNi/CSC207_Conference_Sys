package views.event;

import controllers.event.LocationController;
import enums.ViewEnum;
import presenters.event.LocationPresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for adding locations.
 */
public class LocationAddView implements View {
    private final LocationPresenter presenter;
    private final LocationController controller;
    private final GetInputView getInputView;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>LocationAddView</code> with its accompanying controller and presenter.
     *
     * @param controller given controller
     * @param presenter  given presenter
     */
    public LocationAddView(LocationController controller, LocationPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
        getInputView = new GetInputView(presenter);
    }

    /**
     * Runs the view.
     *
     * @return ViewEnum.VOID
     */
    public ViewEnum runView() {
        presenter.locationCreationHeader();
        boolean nameChosen = false;
        String name = "";
        presenter.namePrompt();
        while (!nameChosen) {
            name = userInput.nextLine();
            if (controller.isNewLocation(name)) nameChosen = true;
            else presenter.nameTakenNotification();
        }
        presenter.capacityPrompt();
        int capacity = getInputView.getPositiveNumber();
        presenter.tablesPrompt();
        int tables = getInputView.getNonNegativeNumber();
        presenter.chairsPrompt();
        int chairs = getInputView.getNonNegativeNumber();
        presenter.internetPrompt();
        boolean hasInternet = getInputView.getBoolean();
        presenter.soundSystemPrompt();
        boolean hasSoundSystem = getInputView.getBoolean();
        presenter.presentationScreenPrompt();
        boolean hasPresentationScreen = getInputView.getBoolean();
        presenter.furtherNotesPrompt();
        String furtherNotes = userInput.nextLine();
        controller.addNewLocation(name, capacity, tables, chairs, hasInternet, hasSoundSystem,
                hasPresentationScreen, furtherNotes);
        presenter.locationCreationSuccessNotification();
        return ViewEnum.VOID;
    }
}

