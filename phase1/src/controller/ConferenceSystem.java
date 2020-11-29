package controller;

import Throwables.*;
import gateway.DataManager;
import use_cases.*;

public class ConferenceSystem {

    /**
     * Runs the entire conference program by calling the run method in this class
     * @param args command-line arguments
     */
    public static void main(String[] args) throws InvalidIntegerException, MessageNotFound, EventNotFoundException, UserNameNotFoundException, UserNotFoundException, AlreadyExistException, EventFullException, EmptyListException {
        ConferenceSystem conferenceSystem = new ConferenceSystem();
        conferenceSystem.run();
    }

    /**
     * Runs the entire conference program starting with the login screen
     */
    public void run() throws InvalidIntegerException, MessageNotFound, EventNotFoundException, UserNameNotFoundException, UserNotFoundException, AlreadyExistException, EventFullException, EmptyListException {
        DataManager dataManager = new DataManager();
        AccountManager accountManager = dataManager.readAccountManager();
        FriendManager friendManager = dataManager.readFriendManager();
        ConversationManager conversationManager = dataManager.readConversationManager();
        EventManager eventManager = dataManager.readEventManager();
        SignupManager signupManager = dataManager.readSignupManager();
        boolean programEnd = false;
        while (!programEnd) {
            StartController startController = new StartController(accountManager, friendManager, conversationManager, eventManager, signupManager);
            programEnd = startController.runStartMenu();
        }
    }
}