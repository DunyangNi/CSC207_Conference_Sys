package controller;

import Throwables.*;
import use_cases.EventManager;
import use_cases.SignupManager;

import java.util.Calendar;

public class EventCreationController {
    private String username;
    private EventManager eventManager;
    private SignupManager signupManager;
    public EventCreationController(String username, EventManager eventManager, SignupManager signupManager){
        this.username =username;
        this.eventManager = eventManager;
        this.signupManager = signupManager;
    }
    public void createEvent(String topic, Calendar time, String location, String speaker) throws PastTimeException, LocationConflictException, SpeakerNameNotFoundException, ConflictException, LocationNotFoundException, TimeConflictException {
        Integer newTalkID = eventManager.addNewTalk(topic, time, location, this.username, speaker);
        signupManager.addEventKey(newTalkID);
    }

    /**
     * cancels a talk with the given id
     * @param id id of talk to cancel
     */
    public void cancelTalk(Integer id) throws EventNotFoundException {
        this.eventManager.cancelTalk(id);
        signupManager.removeEventKey(id);

    }
}
