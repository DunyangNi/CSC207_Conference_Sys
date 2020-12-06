package deprecated;

import java.util.Set;

/**
 * Defines methods that implementing classes must contain to be able to present information from this program to the UI.
 */
public interface OldPresenter {

    //General account methods
    /**
     * Displays general message output.
     *
     * @param output The message to be displayed
     */
    void displayPrompt(String output);

    /**
     * Displays prompt requesting username and password.
     */
    void displayUserPassPrompt();

    /**
     * Displays a collection of all <code>Account</code>s currently saved in the program.
     *
     * @param accounts Collection of all account usernames
     */
    void displayAccountList(Set<String> accounts);

    /**
     * Displays prompts for contact related user commands corresponding to the given action.
     *
     * @param action Type of contact related user command being issued
     */
    void displayContactsPrompt(String action);

    /**
     * Displays a collection of contacts added by the current user.
     *
     * @param user The username of the current user.
     */
    void displayContactList(String user);

    /**
     * Displays a list of usernames belonging to other users that the current user has a <code>Conversation</code> with.
     *
     * @param condition The condition of the current user's conversation list, either empty or not
     * @param recipients The other users that the current user has a conversation with
     */
    void displayConversations(String condition, Set<String> recipients);

    /**
     * Displays invalid input warnings when issuing <code>Conversation</code> related user commands.
     *
     * @param condition The type of invalid input
     */
    void displayConversationsErrors(String condition);

    /**
     * Displays prompts for <code>Message</code> related user commands corresponding to the given action.
     *
     * @param action The type of messaging related user command being issued
     */
    void displayMessagingPrompt(String action);

    /**
     * Displays a schedule of all <code>Talk</code>s saved in the program
     */
    void displayTalkSchedule();

    //Organizer methods

    /**
     * Displays a menu containing all the user commands available to <code>Organizer</code> accounts.
     */
    void displayOrganizerMenu();

    /**
     * Displays prompt for room registration user command.
     */
    void displayRoomRegistration();

    /**
     * Displays prompts for <code>Event</code> registration related user commands.
     *
     * @param action The type of event related user command being issued
     */
    void displayEventPrompt(String action);


    //Speaker methods

    /**
     * Displays a menu containing all the user commands available to <code>Speaker</code> accounts.
     */
    void displaySpeakerMenu();

    /**
     * Displays a schedule of all <code>Talk</code>s the current <code>Speaker</code>-user will be speaking in.
     *
     * @param speaker The current speaker-user's username
     */
    void displaySpeakerTalksSchedule(String speaker);


    //Attendee methods

    /**
     * Displays a menu containing all the user commands available to <code>Attendee</code> accounts.
     */
    void displayAttendeeMenu();

    /**
     * Displays prompts for <code>Talk</code> related user commands corresponding to the given action.
     *
     * @param action The type of talk related user command being issued
     */
    void displayTalkPrompt(String action);

    /**
     * Displays a schedule of all <code>Talk</code>s the current <code>Attendee</code>-user is signed up for.
     *
     * @param attendee The current attendee-user's username
     */
    void displayAttendeeTalkSchedule(String attendee);

    /**
     * Displays prompts if <code>Event</code> schedules are to be downloaded.
     *
     */
    void displayDownloadSchedulePrompt();
}
