package views.event;

import controllers.event.EventController;
import enums.EventTypeEnum;
import enums.ViewEnum;
import exceptions.InvalidEventTypeException;
import exceptions.NoSuggestedLocationsException;
import exceptions.NotEnoughSpeakersException;
import exceptions.OutOfScheduleException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.SpeakerNotFoundException;
import presenters.event.EventCreationPresenter;
import views.factory.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import static enums.EventTypeEnum.*;

/**
 * View responsible for event creation schedule.
 */

public class EventCreationView implements View {
    private final EventController controller;
    private final EventCreationPresenter presenter;
    private final GetInputView getInputView;
    private final GetTimeView getTimeView;
    Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>EventCreationView</code> based on the following parameters
     *
     * @param controller The given EventController
     * @param presenter  The given EventCreationPresenter
     */
    public EventCreationView(EventController controller, EventCreationPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
        getInputView = new GetInputView(presenter);
        getTimeView = new GetTimeView(presenter);
    }

    /**
     * Runs the view.
     *
     * @return ViewEnum.VOID
     */
    public ViewEnum runView() {
        presenter.eventCreationHeader();
        EventTypeEnum eventType = GENERAL_EVENT;
        boolean eventNotChosen = true;
        presenter.eventTypeMenu();
        while (eventNotChosen) {
            presenter.eventTypePrompt();
            eventType = EventTypeEnum.fromString(userInput.nextLine());
            if (eventType != INVALID) eventNotChosen = false;
            else {
                presenter.invalidEventTypeNotification();
            }
        }

        ArrayList<String> speakers = null;
        boolean chosenSpeakers = false;
        while (!chosenSpeakers) {
            speakers = runSpeakerInputInteraction(eventType);
            try {
                controller.checkValidSpeaker(eventType, speakers);
                chosenSpeakers = true;
            } catch (SpeakerNotFoundException e) {
                presenter.invalidSpeakerNotification();
            } catch (NotEnoughSpeakersException e) {
                presenter.notEnoughSpeakersNotification();
            }
        }

        presenter.topicPrompt();
        String topic = userInput.nextLine();

        Calendar time = getTimeView.runTimeView();

        presenter.vipOnlyPrompt();
        boolean vipOnly = getInputView.getBoolean();

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


        ArrayList<String> suggestedLocationStrings;
        try {
            suggestedLocationStrings = controller.getSuggestedLocations(capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen);
        } catch (NoSuggestedLocationsException e) {
            presenter.noSuggestedLocationsNotification();
            return ViewEnum.VOID;
        }
        presenter.displaySuggestedLocations(suggestedLocationStrings);

        boolean chosenLocation = false;
        String location = "";
        while (!chosenLocation) {
            presenter.locationPrompt();
            location = userInput.nextLine();
            if (!controller.isExistingLocation(location)) presenter.invalidLocationNotification();
            else if (!controller.locationMeetsRequirements(location, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen))
                presenter.requirementMismatchNotification();
            else chosenLocation = true;
        }

        try {
            controller.createEvent(eventType, topic, time, location, speakers, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
            presenter.eventCreationSuccessNotification();
            return ViewEnum.VOID;
        } catch (InvalidEventTypeException e) {
            presenter.invalidEventTypeNotification();
        } catch (LocationInUseException e) {
            presenter.inUseLocationNotification();
        } catch (OutOfScheduleException e) {
            presenter.outOfScheduleNotification();
        } catch (SpeakerIsBusyException e) {
            presenter.speakerIsBusyNotification();
        }
        presenter.eventCreationFailureNotification();
        return ViewEnum.VOID;
    }

    /**
     * Asks for and validate the speakers in the event creation process, based on the event type given,
     * and return the speakers for the event
     *
     * @param eventType the event type enum
     * @return speakers for the event to be created
     */
    public ArrayList<String> runSpeakerInputInteraction(EventTypeEnum eventType) {
        ArrayList<String> speakers = new ArrayList<>();
        if (eventType == TALK) {
            presenter.singleSpeakerPrompt();
            speakers.add(userInput.nextLine());
        } else if (eventType == PANEL_DISCUSSION) {
            boolean inputSpeakers = true;
            presenter.multiSpeakerPrompt();
            while (inputSpeakers) {
                String speaker = userInput.nextLine();
                if (speaker.equals("")) inputSpeakers = false;
                else speakers.add(speaker);
            }
        }
        return speakers;
    }
}