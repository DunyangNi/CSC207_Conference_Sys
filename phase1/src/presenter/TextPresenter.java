package presenter;
import use_cases.*;

import java.util.*;

import java.util.Calendar;

/**
 * Presents program information to a text-based UI for display.
 *
 */
public class TextPresenter implements Presenter {
    // TODO: 11/17/20 Consider whether these fields are necessary or what other fields might be required
    private EventManager eventmanager;
    private SignupManager signupManager;
    private FriendManager friendManager;

    /**
     * Creates a <code>TextPresenter</code> without any fields.
     */
    public TextPresenter() {
        super();
    }

    /**
     * Creates a <code>TextPresenter</code> with access to information stored in Manager objects.
     * @param eventManager The program's <code>EventManager</code> object
     * @param friendManager The program's <code>FriendManager</code> object
     * @param signupManager The program's <code>SignupManager</code> object
     */
    public TextPresenter(EventManager eventManager, FriendManager friendManager, SignupManager signupManager) {
        super();
        this.eventmanager = eventManager;
        this.signupManager = signupManager;
        this.friendManager = friendManager;
    }

    //Shared methods

    /**
     * Prints a string prompt to the console UI.
     *
     * @param output The message to be displayed
     */
    public void displayPrompt(String output) {
        System.out.println(output);
    }

    /**
     * Displays string prompt to the console UI requesting username and password.
     */
    public void displayUserPassPrompt() {
        System.out.println("Enter the following information on separate lines:");
        System.out.println("Enter a username:");
        System.out.println("Enter a password:");
    }

    /**
     * Prints string represented collection of all <code>Account</code>s saved in the program to the console UI.
     *
     * @param accounts Collection of all account usernames
     */
    public void displayAccountList(Set<String> accounts) {
        if (!accounts.isEmpty()) {
            System.out.println("List of users");
            System.out.println("---------------------------------------------");
            for (String acct : accounts) {
                System.out.println(acct);
            }
            System.out.println("---------------------------------------------\n");
        } else {
            System.out.println("(No users)");
        }
    }

    /**
     * Prints string represented collection of all <code>Attendee</code>s saved in the program to the console UI.
     *
     * @param allAttendees Collection of all attendee usernames
     */
    public void displayAttendeeList(Set<String> allAttendees) {
        if (!allAttendees.isEmpty()) {
            System.out.println("List of attendees");
            System.out.println("---------------------------------------------");
            for (String attendeeUsername : allAttendees) {
                System.out.println(attendeeUsername);
            }
            System.out.println("---------------------------------------------\n");
            System.out.println("Specify the attendee's username");
        }
    }

    /**
     * Prints string prompts to the console UI for contact related user commands corresponding to the given action.
     *
     * @param action Type of contact related user command being issued
     */
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

    /**
     * Prints a string represented collection of contacts added by the current user to the console UI.
     *
     * @param user The username of the current user.
     */
    public void displayContactList(String user) {
        ArrayList<String> selectedFriends = friendManager.getFriendList(user);
        System.out.println("Your Contacts List:\n");
        if (selectedFriends.isEmpty()) { System.out.println("No one!"); }
        else { for (String friend : selectedFriends) { System.out.println(friend); } }
    }

    /**
     * Prints a string represented list of usernames belonging to other users that the current user has a <code>Conversation</code> with to the console UI.
     *
     * @param condition The condition of the current user's conversation list, either empty or not
     * @param recipients The other users that the current user has a conversation with
     */
    public void displayConversations(String condition, Set<String> recipients) {
        switch (condition){
            case "empty":
                System.out.println("You have no conversations.");
                break;
            case "non_empty":
                System.out.println("[CONVERSATION RECIPIENTS]");
                for (String recipient : recipients) {
                    System.out.println(recipient);
                }
                System.out.println();
                System.out.println("Enter the following information on separate lines:");
                System.out.println("To access a conversation, please enter the recipient's username:");
                System.out.println("How many past messages would you like to see?");
                break;
        }
    }

    /**
     * Prints invalid input warning strings to the console UI when issuing <code>Conversation</code> related user commands.
     *
     * @param condition The type of invalid input
     */
    public void displayConversationsErrors(String condition){
        switch (condition){
            case "mismatch":
                System.out.println("Invalid input");break;
            case "no_conversation":
                System.out.println("You don't have conversation with this person.");break;
            case "no_user":
                System.out.println("This user do not exits");break;
        }
    }

    /**
     * Prints string prompts to the console UI for <code>Message</code> related user commands corresponding to the given action.
     *
     * @param action The type of messaging related user command being issued
     */
    public void displayMessagingPrompt(String action) {
        switch (action) {
            case "allSpeakers":
                System.out.println("Please enter the message that you want to send to all speakers:");
                break;

            case "allAttendees":
                System.out.println("Please enter the message that you want to send to all attendees:");
                break;
            case "aSpeaker":
                System.out.println("Enter the following information on separate lines:");
                System.out.println("Please enter the username of the speaker you wish to message: ");
                System.out.println("Please enter the message you want to send to this speaker:");
                break;
            case "anAttendee":
                System.out.println("Enter the following information on separate lines:");
                System.out.println("Please enter the username of the attendee you want to message: ");
                System.out.println("Please enter the message you want to send the attendee:");
                break;
        }
    }

    /**
     * Prints string represented information for a given <code>Talk</code> to the console UI.
     *
     * @param eventInfo The talk whose information will be displayed
     */
    private void displayTalkInfo(String[] eventInfo) {
        System.out.println("ID: " + eventInfo[4]);
        System.out.println("Topic: " + eventInfo[0]);
        System.out.println("Speaker: " + eventInfo[1]);
        System.out.println("Location: " + eventInfo[2]);
        System.out.println("Time: " + eventInfo[3]);
        System.out.println();
    }

    /**
     * Prints string represented information for a given collection of <code>Talk</code>s to the console UI.
     *
     * Uses <code>displayTalkInfo</code> as a helper method.
     *
     * @param allTalks The collection of talks whose information will be displayed
     */
    private void displayTalks(HashMap<String[], Calendar> allTalks) {
        if (allTalks.keySet().isEmpty()) System.out.println("Nothing!");
        Calendar timeNow = Calendar.getInstance();
        for(String[] eventInfo : allTalks.keySet()) {
            if(timeNow.compareTo(allTalks.get(eventInfo)) < 0) {
                displayTalkInfo(eventInfo);
            }
        }
    }

    /**
     * Prints a string represented schedule of all <code>Talk</code>s saved in the program to the console UI.
     *
     * Uses <code>displayTalks</code> as a helper method.
     */
    public void displayTalkSchedule() {
        HashMap<String[], Calendar> allTalks = eventmanager.fetchSortedTalks();
        System.out.println("Schedule for all talks:\n");
        displayTalks(allTalks);
    }

    //Organizer methods

    /**
     * Prints a string represented menu containing all the user commands available to <code>Organizer</code> accounts to the console UI.
     */
    public void displayOrganizerMenu() {
        System.out.println("=====[ORGANIZER MENU]=====");
        System.out.println("[ACCOUNT]");
        System.out.println("00 = Logout and exit program");
        System.out.println("0  = Logout");
        System.out.println("1  = Register a new speaker account"); //AccountCreationController - createSpeaker
        System.out.println("2  = View list of all accounts"); //Use presenter directly

        System.out.println("\n[CONTACTS]");
        System.out.println("3  = Add a contact");//same as attendee
        System.out.println("4  = Remove a contact");
        System.out.println("5  = View list of all contacts");

        System.out.println("\n[CONVERSATIONS]");
        System.out.println("6  = Message a speaker");//MessageSpeakerController - messageSpeaker
        System.out.println("7  = Message an attendee");//MessageAttendeeController - messageAttendee
        System.out.println("8  = Message all speakers");//MessageSpeakerController - messageAllSpeakers
        System.out.println("9  = Message all attendees");//MessageAttendeeController - messageAllAttendees
        System.out.println("10 = View your conversations");//same as Attendee

        System.out.println("\n[EVENTS]");
        System.out.println("11 = Register a new event room");//LocationController - addNewLocation
        System.out.println("12 = View list of all rooms");//Use presenter
        System.out.println("13 = Register a new event");//EventModifyController - createEvent
        System.out.println("14 = Cancel an event");//EventModifyController - cancelTalk
        System.out.println("15 = Reschedule an event");//EventModifyController - rescheduleTalk
        System.out.println("16 = View talk schedule");//Use presenter
        System.out.println("==========================");
    }

    /**
     * Prints string prompt for room registration user command to the console UI.
     */
    public void displayRoomRegistration() {
        System.out.println("Enter a name for the new room:");
    }

    /**
     * Prints string prompts to the console UI for <code>Event</code> registration related user commands.
     *
     * @param action The type of event related user command being issued
     */
    public void displayEventPrompt(String action) {
        switch (action) {
            case "register":
                System.out.println("Enter the following information on separate lines:");
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

    //Speaker methods

    /**
     * Prints a string represented menu containing all the user commands available to <code>Speaker</code> accounts to the console UI.
     */
    public void displaySpeakerMenu() {
        System.out.println("======[SPEAKER MENU]======");
        System.out.println("[ACCOUNT]");
        System.out.println("00 = Logout and exit program");
        System.out.println("0  = Logout");
        System.out.println("1  = View list of all accounts"); //Use presenter directly

        System.out.println("\n[CONTACTS]");
        System.out.println("2  = add a new contact"); //AddFriendController - addFriend
        System.out.println("3  = remove a contact"); // RemoveFriendController - removeFriend
        System.out.println("4  = view contacts list"); //// should use ViewFriendListPresenter directly

        System.out.println("\n[CONVERSATIONS]");
        System.out.println("5  = message an attendee"); //MessageAttendeeController - messageAttendee
        System.out.println("6  = message all attendees for one or multiple talks you're giving"); //MessageAttendeeController - messageAttendeesAtTalks
        System.out.println("7  = view your conversation with someone"); //ViewConversation - isEmpty, viewMessageFrom

        System.out.println("\n[EVENTS]");
        System.out.println("8  = see a schedule of talks you're giving"); //Use presenter
        System.out.println("9  = see a schedule of all talks"); //use presenter
        System.out.println("==========================");
    }

    /**
     * Prints a string represented schedule of all <code>Talk</code>s the current <code>Speaker</code>-user will be speaking in to the console UI.
     * @param speaker The current speaker-user's username
     */
    public void displaySpeakerTalksSchedule(String speaker) {
        HashMap<String[], Calendar> speakerTalks = eventmanager.fetchSortedTalks(speaker);
        System.out.println("Schedule for talks you're speaking at:\n");
        displayTalks(speakerTalks);
    }

    //Attendee methods

    /**
     * Prints a string represented menu containing all the user commands available to <code>Attendee</code> accounts to the console UI.
     */
    public void displayAttendeeMenu() {
        System.out.println("=====[ATTENDEE MENU]=====");
        System.out.println("[ACCOUNT]");
        System.out.println("What would you like to do?");
        System.out.println("00 = Logout and exit program");
        System.out.println("0  = Logout");

        System.out.println("\n[CONTACTS]");
        System.out.println("1  = add a new contact"); //AddFriendController - addFriend
        System.out.println("2  = remove a contact"); // RemoveFriendController - removeFriend
        System.out.println("3  = see contacts list"); // should use ViewFriendListPresenter directly

        System.out.println("\n[CONVERSATIONS]");
        System.out.println("4  = message another attendee"); //MessageAttendeeController - messageAttendee
        System.out.println("5  = message a speaker"); //MessageSpeakerController - messageSpeaker
        System.out.println("6  = view your conversation with someone"); //ViewConversation - isEmpty, viewMessageFrom

        System.out.println("\n[EVENTS]");
        System.out.println("7  = see talk schedule"); //Use the presenter directly
        System.out.println("8  = sign up for a talk"); //EventSignUpController - signForEvent
        System.out.println("9  = cancel enrolment for a talk"); //EventSignUpController - cancelForEvent
        System.out.println("10 = see a schedule of talks you're attending"); //Use the presenter
        System.out.println("=========================");
    }

    /**
     * Displays string prompts to the console UI for <code>Talk</code> related user commands corresponding to the given action.
     *
     * @param action The type of talk related user command being issued
     */
    public void displayTalkPrompt(String action){
        switch (action) {
            case "attend":
                System.out.println("Please enter the ID of the Talk you wish to attend: ");
                break;
            case "cancel":
                System.out.println("Please enter the ID of the Talk you wish to cancel: ");
                break;
            case "invalid":
                System.out.println("Invalid Talk ID.");
                break;
        }
    }

    /**
     * Displays a string represented schedule of all <code>Talk</code>s the current <code>Attendee</code>-user is signed up for to the console UI.
     * @param attendee The current attendee-user's username
     */
    public void displayAttendeeTalkSchedule(String attendee) {
        HashMap<String[], Calendar> attendeeTalks = eventmanager.fetchSortedTalks();
        System.out.println("Talks you've signed up for:\n");
        Calendar timeNow = Calendar.getInstance();
        if (attendeeTalks.keySet().isEmpty()) System.out.println("Nothing!");
        for(String[] eventInfo : attendeeTalks.keySet()) {
            if(timeNow.compareTo(attendeeTalks.get(eventInfo)) < 0 &&
                    signupManager.isSignedUp(Integer.parseInt(eventInfo[4]), attendee)) {
                displayTalkInfo(eventInfo);
            }
        }
    }
}
