package controller;

import entities.Event;
import use_cases.EventManager;

/**
 * Check if a speaker is the speaker of a given event
 */
public class SpeakerCheckerController {
    private EventManager eventManager;
    public SpeakerCheckerController(EventManager eventManager){
        this.eventManager = eventManager;
    }

    public boolean isSpeakerOf(String speakerName, Integer eventId){
        return eventManager.isSpeakerOfTalk(eventId, speakerName);
    }
}
