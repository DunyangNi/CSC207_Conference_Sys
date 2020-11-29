package controller;

import Throwables.*;
import use_cases.SignupManager;

public class EventSignUpController {
    private SignupManager signupManager;
    private String username;
    public EventSignUpController(SignupManager signupManager, String username){
        this.signupManager = signupManager;
        this.username = username;
    }
    /**
     * Signs attendee up for a talk with the given id
     * @param id talk id
     */
    public void signupForEvent(Integer id) throws EventNotFoundException, EventFullException, AlreadyExistException {
        signupManager.addAttendee(id, username);
    }
    /**
     * Cancels talk with given id
     * @param id talk id
     */
    public void cancelForEvent(Integer id) throws EventNotFoundException, UserNameNotFoundException {
        signupManager.removeAttendee(id, username);
    }
}
