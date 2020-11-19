package presenter;
import use_cases.*;

import java.util.*;

import java.util.Calendar;

public class TextPresenter extends Presenter{
    // TODO: 11/17/20 Consider whether these fields are necessary or what other fields might be required
    private EventManager eventmanager;
    private SignupManager signupManager;
    private FriendManager friendManager;

    // TODO: 11/16/20 Find a better way to do this?

    public TextPresenter() {
        super();
    }

    public TextPresenter(EventManager eventManager, FriendManager friendManager, SignupManager signupManager) {
        super();
        this.eventmanager = eventManager;
        this.signupManager = signupManager;
        this.friendManager = friendManager;
    }

    //Shared methods
    @Override
    public void displayPrompt(String output) {
        System.out.println(output);
    }

    @Override
    public void displayTalkSchedule() {
        HashMap<String[], Calendar> allTalks = eventmanager.fetchSortedTalks();
        System.out.println("Schedule for all talks:\n");
        if (allTalks.keySet().isEmpty()) System.out.println("Nothing!");
        Calendar timeNow = Calendar.getInstance();
        for(String[] eventInfo : allTalks.keySet()) {
            if(timeNow.compareTo(allTalks.get(eventInfo)) < 0) {
                System.out.println("ID: " + eventInfo[4]);
                System.out.println("Topic: " + eventInfo[0]);
                System.out.println("Speaker: " + eventInfo[1]);
                System.out.println("Location: " + eventInfo[2]);
                System.out.println("Time: " + eventInfo[3]);
                System.out.println();
            }
        }
    }

    //Organizer methods
    @Override
    public void displayOrganizerMenu() {
        System.out.println("=====[ORGANIZER MENU]=====");
        System.out.println("[ACCOUNT]");
        System.out.println("00  = Logout and exit program");
        System.out.println("0  = Logout");
        System.out.println("2  = Register a new speaker account");
        System.out.println("17 = [FOR TESTING PURPOSES] Delete an account");
        System.out.println("16 = View list of all accounts");

        System.out.println("\n[CONTACTS]");
        System.out.println("11 = Add a contact");
        System.out.println("12 = Remove a contact");
        System.out.println("13 = View list of all contacts");

        System.out.println("\n[CONVERSATIONS]");
        System.out.println("7  = Message a speaker");
        System.out.println("9  = Message an attendee");
        System.out.println("6  = Message all speakers");
        System.out.println("8  = Message all attendees");
        System.out.println("14 = View your conversations");

        System.out.println("\n[EVENTS]");
        System.out.println("1  = Register a new event room");
        System.out.println("15 = View list of all rooms");
        System.out.println("3  = Register a new event");
        System.out.println("4  = Cancel an event");
        System.out.println("5  = Reschedule an event");
        System.out.println("10 = View talk schedule");
        System.out.println("==========================");
        System.out.println("Enter a command (1-16):");
    }

    @Override
    public void displayRoomRegistration() {
        System.out.println("Enter a name for the new room:");
    }

    @Override
    public void displayEventPrompt(String action) {
        switch (action) {
            case "register":
                System.out.println("Enter the speaker's username:");
                System.out.println("Enter the event room:");
                System.out.println("Enter the event topic:");
                System.out.println("Enter the event time:");
                break;
            case "cancel":
                System.out.println("Please enter the ID of a talk you wish to cancel: ");
                break;
            case "reschedule":
                System.out.println("Please enter the ID of a talk you wish to reschedule: ");
                break;
        }
    }

    @Override
    public void displayAccountRegistration() {
        System.out.println("Enter a username:");
        System.out.println("Enter a password:");
    }

    @Override
    public void displayMessagingPrompt(String action) {
        switch (action) {
            case "allSpeakers":
                System.out.println("Please enter the message that you want to send to all speakers:");
                break;

            case "allAttendees":
                System.out.println("Please enter the message that you want to send to all attendees:");
                break;
            case "aSpeaker":
                System.out.println("Please enter the username of the speaker you wish to message: ");
                System.out.println("Please enter the message you want to send to this speaker:");
                break;
            case "anAttendee":
                System.out.println("Please enter the username of the attendee you want to message: ");
                System.out.println("Please enter the message you want to send the attendee:");
                break;
        }
    }

    @Override
    public void displayContactsPrompt(String action) {
        switch (action) {
            case "add":
                System.out.println("Please enter the username of a contact to add: ");
                break;
            case "remove":
                System.out.println("Please enter the username of a contact to remove: ");
                break;
        }
    }

    @Override
    public void displayConversations(Set<String> recipients) {
            if (recipients.isEmpty()) {
                System.out.println("You have no conversations.");
            } else {
                System.out.println("[CONVERSATION RECIPIENTS]");
                for (String recipient : recipients) {
                    System.out.println(recipient);
                }
                System.out.println();
                System.out.println("To access a conversation, please enter the recipient's username:");
                System.out.println("How many past messages would you like to see?");
            }
    }

    @Override
    public void displaySpeakerMenu() {
        System.out.println("=====[SPEAKER MENU]=====");
        System.out.println("[ACCOUNT]");
        System.out.println("00  = Logout and exit program");
        System.out.println("0  = Logout");
        System.out.println("16 = View list of all accounts");

        System.out.println("\n[CONTACTS]");
        System.out.println("5 = add a new contact");
        System.out.println("6 = remove a contact");
        System.out.println("7 = view contacts list");

        System.out.println("\n[CONVERSATIONS]");
        System.out.println("2 = message an attendee");
        System.out.println("3 = message all attendees for one or multiple talks you're giving");
        System.out.println("8 = view your conversation with someone");

        System.out.println("\n[EVENTS]");
        System.out.println("1 = see a schedule of talks you're giving");
        System.out.println("4 = see a schedule of all talks");
        System.out.println("==========================");
        System.out.println("Enter a command (1-16):");
    }

    @Override
    public void displayAttendeeMenu() {
        System.out.println("=====[ATTENDEE MENU]=====");
        System.out.println("[ACCOUNT]");
        System.out.println("What would you like to do?");
        System.out.println("00  = Logout and exit program");
        System.out.println("0  = Logout");

        System.out.println("\n[CONTACTS]");
        System.out.println("7 = add a new contact");
        System.out.println("8 = remove a contact");
        System.out.println("9 = see contacts list");

        System.out.println("\n[CONVERSATIONS]");
        System.out.println("5 = message another attendee");
        System.out.println("6 = message a speaker");
        System.out.println("10 = view your conversation with someone");

        System.out.println("\n[EVENTS]");
        System.out.println("1 = see talk schedule");
        System.out.println("2 = sign up for a talk");
        System.out.println("3 = cancel enrolment for a talk");
        System.out.println("4 = see a schedule of talks you're attending");
        System.out.println("==========================");
        System.out.println("Enter a command (1-16):");
    }

    @Override
    public void displayAttendeeTalkSchedule(String attendee) {
        HashMap<String[], Calendar> attendeeTalks = eventmanager.fetchSortedTalks();
        System.out.println("Talks you've signed up for:\n");
        Calendar timeNow = Calendar.getInstance();
        if (attendeeTalks.keySet().isEmpty()) System.out.println("Nothing!");
        for(String[] eventInfo : attendeeTalks.keySet()) {
            if(timeNow.compareTo(attendeeTalks.get(eventInfo)) < 0 &&
                    signupManager.isSignedUp(Integer.parseInt(eventInfo[4]), attendee)) {
                System.out.println("ID: " + eventInfo[4]);
                System.out.println("Topic: " + eventInfo[0]);
                System.out.println("Speaker: " + eventInfo[1]);
                System.out.println("Location: " + eventInfo[2]);
                System.out.println("Time: " + eventInfo[3]);
                System.out.println();
            }
        }
    }

    @Override
    public void displaySpeakerTalksSchedule(String speaker) {
        HashMap<String[], Calendar> speakerTalks = eventmanager.fetchSortedTalks(speaker);
        System.out.println("Schedule for talks you're speaking at:\n");
        if (speakerTalks.keySet().isEmpty()) System.out.println("Nothing!");
        Calendar timeNow = Calendar.getInstance();
        for(String[] eventInfo : speakerTalks.keySet()) {
            if(timeNow.compareTo(speakerTalks.get(eventInfo)) < 0) {
                System.out.println("ID: " + eventInfo[4]);
                System.out.println("Topic: " + eventInfo[0]);
                System.out.println("Speaker: " + eventInfo[1]);
                System.out.println("Location: " + eventInfo[2]);
                System.out.println("Time: " + eventInfo[3]);
                System.out.println();
            }
        }
    }

    @Override
    public void displayFriendList(String user) {
        ArrayList<String> selectedFriends = friendManager.getFriendList(user);
        System.out.println("Your Contacts List:\n");
        if (selectedFriends.isEmpty()) { System.out.println("No one!"); }
        else { for (String friend : selectedFriends) { System.out.println(friend); } }
    }
}
