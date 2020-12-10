package views.event;

import controllers.event.EventController;
import enums.EventTypeEnum;
import enums.ViewEnum;
import exceptions.*;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.SpeakerNotFoundException;
import presenters.event.EventCreationPresenter;
import views.View;

import java.util.*;

import static enums.EventTypeEnum.*;

public class EventCreationView implements View {
    private final EventController eventController;
    private final EventCreationPresenter eventCreationPresenter;
    private final TimeView timeView = new TimeView();
    Scanner userInput = new Scanner(System.in);

    public EventCreationView(EventController eventController, EventCreationPresenter presenter) {
        this.eventController = eventController;
        eventCreationPresenter = presenter;
    }

    public ViewEnum runView() {
        eventCreationPresenter.startPrompt();

        EventTypeEnum eventType = GENERAL_EVENT;
        boolean eventNotChosen = true;
        eventCreationPresenter.eventTypeMenu();
        while(eventNotChosen) {
            eventCreationPresenter.eventTypePrompt();
            eventType = EventTypeEnum.fromString(userInput.nextLine());
            if (eventType != INVALID) eventNotChosen = false;
            else { eventCreationPresenter.invalidEventTypeNotification(); }
        }

        ArrayList<String> speakers = null;
        boolean chosenSpeakers = false;
        while (!chosenSpeakers) {
            speakers = runSpeakerInputInteraction(eventType);
            try {
                eventController.checkValidSpeaker(eventType, speakers);
                chosenSpeakers = true;
            }
            catch (SpeakerNotFoundException e) { eventCreationPresenter.invalidSpeakerPrompt(); }
            catch (NotEnoughSpeakersException e) { eventCreationPresenter.notEnoughSpeakersPrompt(); }
        }

        eventCreationPresenter.topicPrompt();
        String topic = userInput.nextLine();

        Calendar time = timeView.runTimeView();

        eventCreationPresenter.vipOnlyPrompt();
        boolean valid = false;
        boolean vipOnly = false;
        while (!valid) {
            String input = userInput.nextLine();
            if (input.equals("Y")) {
                vipOnly = true;
                valid = true;
            } else if (input.equals("N")) {
                valid = true;
            } else { eventCreationPresenter.invalidYesNoPrompt(); }
        }

        eventCreationPresenter.requirementsPrompt();

        boolean capacityInput = false;
        int capacity = 0;
        eventCreationPresenter.capacityPrompt();
        while (!capacityInput) {
            try {
                capacity = Integer.parseInt(userInput.nextLine());
                if (capacity <= 0) eventCreationPresenter.positiveNumberPrompt();
                else capacityInput = true;
            }
            catch (NumberFormatException e) { eventCreationPresenter.invalidNumberPrompt(); }
        }

        boolean tablesInput = false;
        int tables = 0;
        eventCreationPresenter.tablesPrompt();
        while (!tablesInput) {
            try {
                tables = Integer.parseInt(userInput.nextLine());
                if (tables < 0) eventCreationPresenter.nonNegativeNumberPrompt();
                else tablesInput = true;
            }
            catch (NumberFormatException e) { eventCreationPresenter.invalidNumberPrompt(); }
        }

        boolean chairsInput = false;
        int chairs = 0;
        eventCreationPresenter.chairsPrompt();
        while (!chairsInput) {
            try {
                chairs = Integer.parseInt(userInput.nextLine());
                if (chairs < 0) eventCreationPresenter.nonNegativeNumberPrompt();
                else chairsInput = true;
            }
            catch (NumberFormatException e) { eventCreationPresenter.invalidNumberPrompt(); }
        }

        boolean internetInput = false;
        boolean hasInternet = false;
        eventCreationPresenter.internetPrompt();
        while (!internetInput) {
            String input = userInput.nextLine();
            if (input.equals("Y")) {
                hasInternet = true;
                internetInput = true;
            } else if (input.equals("N")) {
                internetInput = true;
            } else { eventCreationPresenter.invalidYesNoPrompt(); }
        }

        boolean soundSystemInput = false;
        boolean hasSoundSystem = false;
        eventCreationPresenter.soundSystemPrompt();
        while (!soundSystemInput) {
            String input = userInput.nextLine();
            if (input.equals("Y")) {
                hasSoundSystem = true;
                soundSystemInput = true;
            } else if (input.equals("N")) {
                soundSystemInput = true;
            } else { eventCreationPresenter.invalidYesNoPrompt(); }
        }

        boolean presentationScreenInput = false;
        boolean hasPresentationScreen = false;
        eventCreationPresenter.presentationScreenPrompt();
        while (!presentationScreenInput) {
            String input = userInput.nextLine();
            if (input.equals("Y")) {
                hasPresentationScreen = true;
                presentationScreenInput = true;
            } else if (input.equals("N")) {
                presentationScreenInput = true;
            } else { eventCreationPresenter.invalidYesNoPrompt(); }
        }

        ArrayList<String> suggestedLocationStrings;
        try {
            suggestedLocationStrings = eventController.getSuggestedLocations(capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen);
        } catch (NoSuggestedLocationsException e) {
            eventCreationPresenter.noSuggestedLocationsPrompt();
            return ViewEnum.VOID;
        }
        eventCreationPresenter.displaySuggestedLocations(suggestedLocationStrings);

        boolean chosenLocation = false;
        String location = "";
        while (!chosenLocation) {
            eventCreationPresenter.locationPrompt();
            location = userInput.nextLine();
            if (!eventController.isExistingLocation(location)) eventCreationPresenter.invalidLocationNotification();
            else if (!eventController.locationMeetsRequirements(location, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen))
                eventCreationPresenter.requirementMismatchPrompt();
            else chosenLocation = true;
        }

        try {
            eventController.createEvent(eventType, topic, time, location, speakers, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
            eventCreationPresenter.exitPrompt();
            return ViewEnum.VOID;
        } catch (InvalidEventTypeException e) {
            eventCreationPresenter.invalidEventTypeNotification();
        } catch (LocationInUseException e) {
            eventCreationPresenter.inUseLocationNotification();
        } catch (OutOfScheduleException e) {
            eventCreationPresenter.outOfScheduleNotification();
        } catch (SpeakerIsBusyException e) {
            eventCreationPresenter.speakerIsBusyNotification();
        }
        eventCreationPresenter.cancelExitPrompt();
        return ViewEnum.VOID;
    }

    private ArrayList<String> runSpeakerInputInteraction(EventTypeEnum eventType) {
        ArrayList<String> speakers = new ArrayList<>();
        if (eventType == TALK) {
            eventCreationPresenter.singleSpeakerPrompt();
            speakers.add(userInput.nextLine());
        } else if (eventType == PANEL_DISCUSSION) {
            boolean inputSpeakers = true;
            eventCreationPresenter.multiSpeakerPrompt();
            while (inputSpeakers) {
                String speaker = userInput.nextLine();
                if (speaker.equals("")) inputSpeakers = false;
                else speakers.add(speaker);
            }
        }
        return speakers;
    }
}