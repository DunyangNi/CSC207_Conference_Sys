package controller;

import gateway.DataManager;
import use_cases.*;

public class ConferenceSystem {
    public static void main(String[] args) {
        ConferenceSystem conferenceSystem = new ConferenceSystem();
        conferenceSystem.run();
    }

    public void run() {
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