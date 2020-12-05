package controllers.event;

import gateway.DataManager;
import use_cases.event.EventManager;

/**
 * Check if a speaker is the speaker of a given event
 */
public class SpeakerCheckerController {
    private EventManager eventManager;
    public SpeakerCheckerController(DataManager dm){
        this.eventManager = dm.getEventManager();
    }

    public boolean isSpeakerOf(String speakerName, Integer eventId){
        return eventManager.isSpeakerOfTalk(eventId, speakerName);
    }
}
