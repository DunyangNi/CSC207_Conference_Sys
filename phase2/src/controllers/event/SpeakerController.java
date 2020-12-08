package controllers.event;

import gateways.DataManager;
import use_cases.account.AccountManager;
import use_cases.event.EventManager;

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

    public boolean isSpeakerOf(String speakerName, Integer eventId){
        return eventManager.isSpeakerOfTalk(eventId, speakerName);
    }

    public boolean isValidSpeaker(String speaker) {
        return accountManager.containsSpeaker(speaker);
    }
}
