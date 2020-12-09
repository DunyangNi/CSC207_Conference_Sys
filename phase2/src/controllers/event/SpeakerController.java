package controllers.event;

import enums.EventTypeEnum;
import exceptions.NotEnoughSpeakersException;
import exceptions.not_found.SpeakerNotFoundException;
import gateways.DataManager;
import use_cases.account.AccountManager;
import use_cases.event.EventManager;

import java.util.ArrayList;

import static enums.EventTypeEnum.PANEL_DISCUSSION;
import static enums.EventTypeEnum.TALK;

/**
 * Check if a speaker is the speaker of a given event
 */
public class SpeakerController {
    private final EventManager eventManager;
    private final AccountManager accountManager;

    public SpeakerController(DataManager dm){
        this.accountManager = dm.getAccountManager();
        this.eventManager = dm.getEventManager();
    }

    public void checkValidSpeaker(EventTypeEnum eventType, ArrayList<String> speakers) throws SpeakerNotFoundException, NotEnoughSpeakersException {
        if (eventType == TALK) {
            if (!accountManager.containsSpeaker(speakers.get(0))) throw new SpeakerNotFoundException();
        } else if (eventType == PANEL_DISCUSSION) {
            if (speakers.size() < 2) throw new NotEnoughSpeakersException();
            for (String speaker : speakers) { if (!accountManager.containsSpeaker(speaker)) throw new SpeakerNotFoundException(); }
        }
    }
}
