package views.event;

import controllers.event.EventController;
import controllers.event.LocationController;
import controllers.event.SpeakerController;
import enums.EventTypeEnum;
import exceptions.InvalidEventTypeException;
import exceptions.NoSuggestedLocationsException;
import exceptions.OutOfScheduleException;
import exceptions.PastTimeException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import presenters.event.EventCreationPresenter;

import java.util.*;

import static enums.EventTypeEnum.*;

public class EventCreationView {
    private final EventController eventController;
    private final SpeakerController speakerController;
    private final EventCreationPresenter eventCreationPresenter;
    private final LocationController locationController;
    private final TimeView timeView = new TimeView();
    Scanner userInput = new Scanner(System.in);

    public EventCreationView(EventController controller, SpeakerController controller2, LocationController locationController, EventCreationPresenter presenter ) {
        eventController = controller;
        speakerController = controller2;
        eventCreationPresenter = presenter;
        this.locationController = locationController;
    }

    public void runView() {
        eventCreationPresenter.startPrompt();

        EventTypeEnum eventType = GENERAL_EVENT;
        boolean eventNotChosen = true;
        eventCreationPresenter.eventTypeMenu();
        while(eventNotChosen) {
            eventCreationPresenter.eventTypePrompt();
            eventType = EventTypeEnum.fromString(userInput.nextLine());
            if (eventType != INVALID) eventNotChosen = false;
            else { eventCreationPresenter.invalidEventTypePrompt(); }
        }

        ArrayList<String> speakers = null;
        boolean chosenSpeakers = false;
        while (!chosenSpeakers) {
            speakers = runSpeakerInputInteraction(eventType);
            if (validSpeakerInput(eventType, speakers)) chosenSpeakers = true;
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
                if (capacity <= 0) eventCreationPresenter.invalidNumberPrompt();
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
                if (tables < 0) eventCreationPresenter.invalidNumberPrompt();
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
                if (chairs < 0) eventCreationPresenter.invalidNumberPrompt();
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
            suggestedLocationStrings = locationController.getSuggestedLocations(capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen);
        } catch (NoSuggestedLocationsException e) {
            eventCreationPresenter.noSuggestedLocationsPrompt();
            return;
        }
        eventCreationPresenter.displaySuggestedLocations(suggestedLocationStrings);

        boolean chosenLocation = false;
        String location = "";
        while (!chosenLocation) {
            eventCreationPresenter.locationPrompt();
            location = userInput.nextLine();
            if (!locationController.isExistingLocation(location)) eventCreationPresenter.invalidLocationPrompt();
            else if (locationController.locationMeetsRequirements(location, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen))
                eventCreationPresenter.requirementMismatchPrompt();
            else chosenLocation = true;
        }

        try {
            eventController.createEvent(eventType, topic, time, location, speakers, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
            eventCreationPresenter.exitPrompt();
            return;
        } catch (InvalidEventTypeException e) {
            eventCreationPresenter.invalidEventTypePrompt();
        } catch (LocationInUseException e) {
            eventCreationPresenter.inUseLocationPrompt();
        } catch (OutOfScheduleException e) {
            eventCreationPresenter.outOfSchedulePrompt();
        } catch (SpeakerIsBusyException e) {
            eventCreationPresenter.speakerIsBusyPrompt();
        }
        eventCreationPresenter.cancelExitPrompt();
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

    private boolean validSpeakerInput(EventTypeEnum eventType, ArrayList<String> speakers) {
        if (eventType == TALK) {
            if (!speakerController.isValidSpeaker(speakers.get(0))) {
                eventCreationPresenter.invalidSpeakerPrompt(speakers.get(0));
                return false;
            }
        } else if (eventType == PANEL_DISCUSSION) {
            if (speakers.size() < 2) {
                eventCreationPresenter.notEnoughSpeakersPrompt();
                return false;
            }
            for (String speaker : speakers) {
                if (!speakerController.isValidSpeaker(speaker)) {
                    eventCreationPresenter.invalidSpeakerPrompt(speaker);
                    return false;
                }
            }
        }
        return true;
    }
}