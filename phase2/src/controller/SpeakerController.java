package controller;

import Throwables.ObjectNotFoundException;
import use_cases.*;
import java.util.*;
import java.lang.*;
import presenter.*;

public class SpeakerController extends AccountController {
    /**
     * facilitates interaction with speaker upon login
     * @param username speaker username
     * @param accountmanager manages data of all accounts in program
     * @param friendManager manages friendlist functionality
     * @param conversationManager manages messaging functionality
     * @param eventmanager manages event data
     * @param presenter specifies the UI
     */
    public SpeakerController(String username, AccountManager accountmanager, FriendManager friendManager,
                             ConversationManager conversationManager, EventManager eventmanager,
                             Presenter presenter) {
        super(username, accountmanager, friendManager, conversationManager, eventmanager, presenter);
    }

    /**
     * Displays all talks this speaker is giving in the future
     */
    public void SeeSpeakerTalkSchedule() {
        this.presenter.displaySpeakerTalksSchedule(this.username);
    }

    /**
     * Interacts with speaker via menu of options
     * @return True if the speaker wishes to terminate the program
     */
    @Override
    public boolean runInteraction() {
        boolean programEnd = false;
        boolean loggedIn = true;
        presenter.displaySpeakerMenu();
        Scanner userInput = new Scanner(System.in);
        String command = userInput.nextLine();
        while(loggedIn){
            switch (command) {
                case "00":
                    loggedIn = false;
                    programEnd = true;
                    break;
                case "0":
                    loggedIn = false;
                    break;
                case "1":
                    Set<String> accounts = accountManager.getAccountHashMap().keySet();
                    presenter.displayAccountList(accounts);
                    break;
                case "2":
                    accounts = accountManager.getAccountHashMap().keySet();
                    presenter.displayAccountList(accounts);
                    presenter.displayContactsPrompt("add");
                    String contactToAdd = userInput.nextLine();
                    friendController.addFriend(contactToAdd);
                    break;
                case "3":
                    presenter.displayContactList(username);
                    presenter.displayContactsPrompt("remove");
                    String removeContact = userInput.nextLine();
                    friendController.removeFriend(removeContact);
                    break;
                case "4":
                    presenter.displayContactList(username);
                    break;
                case "5":
                    Set<String> allAttendees = accountManager.getAttendeeHashMap().keySet();
                    if (!allAttendees.isEmpty()) {
                        this.presenter.displayPrompt("List of attendees");
                        this.presenter.displayPrompt("---------------------------------------------");
                        for (String attendeeUsername : allAttendees) {
                            this.presenter.displayPrompt(attendeeUsername);
                        }
                        this.presenter.displayPrompt("---------------------------------------------\n");
                        this.presenter.displayPrompt("Specify the attendee's username");
                        String attendee = userInput.nextLine();
                        this.presenter.displayPrompt("Please enter your message to send: ");
                        String message = userInput.nextLine();
                        if (allAttendees.contains(attendee)) {
                            messageController.messageAttendee(message, attendee);
                        } else {
                            this.presenter.displayPrompt("The entered recipient username is invalid.");
                        }
                    } else {
                        this.presenter.displayPrompt("(No attendees)");
                    }
                    break;
                case "6":
                    ArrayList<Integer> selectedSpeakerTalks = new ArrayList<>();
                    boolean doneAddingTalks = false;
                    while (!doneAddingTalks) {
                        this.presenter.displayPrompt("Please enter the ID of a Talk you are giving: ");
                        Integer id = Integer.parseInt(userInput.nextLine());
                        if (eventManager.isSpeakerOfTalk(id, username)) {
                            selectedSpeakerTalks.add(id);
                        } else {
                            this.presenter.displayPrompt("Invalid ID. You are not speaking at this talk.");
                            continue;
                        }
                        this.presenter.displayPrompt("Would you like to add another Talk? (1 = yes, 0 = no)");
                        int response = userInput.nextInt();
                        userInput.nextLine();
                        doneAddingTalks = response == 0;
                    }
                    this.presenter.displayPrompt("Please enter your message to send: ");
                    String message = userInput.nextLine();
                    messageController.messageAttendeesAtTalks(selectedSpeakerTalks, message);
                    break;
                case "7":
                    try {
                        Set<String> myConversations = conversationManager.getAllUserConversationRecipients(username);
                        if (myConversations.isEmpty()) {
                            this.presenter.displayConversations("empty", myConversations);
                        } else {
                            this.presenter.displayConversations("non_empty", myConversations);
                            String user = userInput.nextLine();
                            int pastMessages = userInput.nextInt();
                            userInput.nextLine();
                            this.viewMessagesFrom(user, pastMessages);
                        }
                    } catch (InputMismatchException e) {
                        this.presenter.displayConversationsErrors("mismatch");
                    } catch (ObjectNotFoundException e) {
                        this.presenter.displayConversationsErrors("no_user");
                    } catch (NullPointerException e) {
                        this.presenter.displayConversationsErrors("no_conversation");
                    }
                    break;
                case "8":
                    this.SeeSpeakerTalkSchedule();
                    break;
                case "9":
                    this.SeeTalkSchedule();
                    break;
                case "*":
                    presenter.displaySpeakerMenu();
                    break;
                default:
                    presenter.displayPrompt("Invalid input, please try again:\n");
            }
            if (loggedIn) {
                presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
                command = userInput.nextLine();
            }
        }
        return programEnd;
    }
}
