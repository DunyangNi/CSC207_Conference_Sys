package views.event;

import controllers.event.EventController;
import controllers.event.SpeakerController;
import enums.EventTypeEnum;
import exceptions.InvalidEventTypeException;
import exceptions.InvalidTimeException;
import exceptions.PastTimeException;
import exceptions.conflict.LocationInUseException;
import exceptions.not_found.LocationNotFoundException;
import gateways.DataManager;
import presenters.event.EventCreationPresenter;

import java.util.*;

import static enums.EventTypeEnum.*;

public class EventCreationView {
    private final EventController eventController;
    private final SpeakerController speakerController;
    private final EventCreationPresenter eventCreationPresenter = new EventCreationPresenter();
    Scanner userInput = new Scanner(System.in);

    public EventCreationView(DataManager dataManager) {
        eventController = new EventController(dataManager);
        speakerController = new SpeakerController(dataManager);
    }

    public void runInteraction() {
        eventCreationPresenter.startPrompt();

        EventTypeEnum eventType = GENERAL_EVENT;
        boolean eventNotChosen = true;
        eventCreationPresenter.eventTypeMenu();
        eventCreationPresenter.eventTypePrompt();
        while(eventNotChosen) {
            eventType = EventTypeEnum.fromString(userInput.nextLine());
            if (eventType != INVALID) eventNotChosen = false;
            else { eventCreationPresenter.invalidEventTypePrompt(); eventCreationPresenter.eventTypePrompt(); }
        }

        ArrayList<String> speakers = null;
        boolean chosenSpeakers = false;
        while (!chosenSpeakers) {
            speakers = runSpeakerInputInteraction(eventType);
            if (validSpeakerInput(eventType, speakers)) chosenSpeakers = true;
        }

        eventCreationPresenter.topicPrompt();
        String topic = userInput.nextLine();

        eventCreationPresenter.timePrompt();
        boolean valid = false;
        Calendar time = Calendar.getInstance();
        while (!valid){
            try {
                time = parseTime();
                valid = true;
            } catch (Exception e) { eventCreationPresenter.invalidTimePrompt(); }
        }

        eventCreationPresenter.capacityPrompt();
        valid = false;
        int capacity = 0;
        while (!valid){
            try {
                capacity = Integer.parseInt(userInput.nextLine());
                if (capacity <= 0) {
                    eventCreationPresenter.invalidCapacityPrompt();
                    continue;
                }
                valid = true;
            }
            catch (NumberFormatException e) { eventCreationPresenter.invalidCapacityPrompt(); }
        }

        eventCreationPresenter.vipOnlyPrompt();
        valid = false;
        boolean vipOnly = false;
        while (!valid) {
            String input = userInput.nextLine();
            if (input.equals("Y")) {
                vipOnly = true;
                valid = true;
            } else if (input.equals("N")) {
                valid = true;
            } else { eventCreationPresenter.invalidInputPrompt(); }
        }

        // TODO: TO BE REDONE
        eventCreationPresenter.locationPrompt();
        String location = userInput.nextLine();

        try {
            eventController.createEvent(eventType, topic, time, location, speakers, capacity, vipOnly);
            eventCreationPresenter.exitPrompt();
        } catch (LocationNotFoundException e) {
            eventCreationPresenter.invalidLocationPrompt();
        } catch (InvalidEventTypeException e) {
            eventCreationPresenter.invalidEventTypePrompt();
        } catch (LocationInUseException e) {
            eventCreationPresenter.inUseLocationPrompt();
        } catch (PastTimeException e) {
            eventCreationPresenter.pastTimePrompt();
        } catch (InvalidTimeException e) {
            eventCreationPresenter.invalidTimePrompt();
        }
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

    private Calendar parseTime() {
        eventCreationPresenter.timeYearPrompt();
        int year = Integer.parseInt(userInput.nextLine());
        eventCreationPresenter.timeMonthPrompt();
        int month = Integer.parseInt(userInput.nextLine()) - 1;
        eventCreationPresenter.timeDayPrompt();
        int day = Integer.parseInt(userInput.nextLine());
        eventCreationPresenter.timeHourPrompt();
        int hour = Integer.parseInt(userInput.nextLine());
        Calendar newTime = Calendar.getInstance();
        newTime.set(year, month, day, hour, 0, 0);
        newTime.clear(Calendar.MILLISECOND);
        eventCreationPresenter.selectedTimePrompt(newTime);
        return newTime;
    }
}