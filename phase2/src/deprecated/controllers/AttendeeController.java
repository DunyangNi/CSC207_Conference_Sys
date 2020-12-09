package deprecated;

import use_cases.*;

import java.lang.*;

import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.event.EventManager;

public class AttendeeController extends AccountController {
//    private final SignupController signupController = new SignupController(username, am, em);
//
    /**
     * facilitates interaction with attendee upon login
     * @param username attendee username
     * @param accountManager data about all accounts in the program
     * @param contactManager contact information
     * @param conversationManager conversation data
     * @param eventmanager event data
     */
//    public AttendeeController(String username, AccountManager accountManager, ContactManager contactManager, ConversationManager conversationManager, EventManager eventmanager) {
//        super(username, accountManager, contactManager, conversationManager, eventmanager);
//    }
//
//    /**
//     * Signs up <code>Attendee</code> user for a <code>Event</code> of a given ID.
//     *
//     * @param id given ID of <code>Event</code>
//     */
//    public void signupForTalk(Integer id) {
//        signupController.signupForEvent(id);
//    }
//
//    /**
//     * Cancels signing up <code>Attendee</code> user for a <code>Event</code> of a given ID.
//     *
//     * @param id given ID of <code>Event</code>
//     */
//    public void cancelSignupForTalk(Integer id) {
//        signupController.cancelSignupForEvent(id);
//    }
//
//    /**
//     * displays the schedule of talks that the attendee is attending
//     */
//    public void seeAttendeeTalkSchedule() {
//        this.oldPresenter.displayAttendeeTalkSchedule(this.username);
//    }

    /**
     * Interacts with attendee via a menu of options
     * @return True if attendee wishes to terminate the program
     */
    @Override
    public boolean runInteraction() {
        boolean programEnd = false;
//        boolean loggedIn = true;
//        Scanner userInput = new Scanner(System.in);
//        AttendeeMenuEnum[] commandlist = AttendeeMenuEnum.values();
//        String command = userInput.nextLine();
//
//        boolean validinput = false;
//        AttendeeMenuEnum enumRequest = AttendeeMenuEnum.EXIT;
//
//        while (!validinput) {
//            for(AttendeeMenuEnum commandEnum: commandlist){
//                if (commandEnum.stringValue.equals(command)) {
//                    validinput = true;
//                    enumRequest = commandEnum;
//                    break;
//                }
//            }
//            if(!validinput){
//                oldPresenter.displayPrompt("Invalid input, please try again:\n");
//                oldPresenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
//                command = userInput.nextLine();
//            }
//        }
//
//        while(loggedIn){
//            switch (enumRequest) {
//                case EXIT:
//                    loggedIn = false;
//                    programEnd = true;
//                    break;
//                case LOGOUT:
//                    loggedIn = false;
//                    break;
//                case ADD_CONTACT:
//                    this.oldPresenter.displayContactsPrompt("add");
//                    String contactToAdd = userInput.nextLine();
//                    try {
//                        contactController.addFriend(contactToAdd);
//                    } catch (UserNotFoundException | FriendNotFoundException | AlreadyFriendException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case REMOVE_CONTACT:
//                    this.oldPresenter.displayContactsPrompt("remove");
//                    String contactToRemove = userInput.nextLine();
//                    try {
//                        contactController.removeFriend(contactToRemove);
//                    } catch (ObjectNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case VIEW_CONTACTS:
//                    this.viewContactList();
//                    break;
//                case MESSAGE_ATTENDEE: {
//                    //messageAttendee(String message, String attendeeUsername)
//                    this.oldPresenter.displayMessagingPrompt("anAttendee");
//                    //String line1 = sc.nextLine();
//                    String attendeeUsername = userInput.nextLine();
//                    //line1 = sc.nextLine();
//                    String message = userInput.nextLine();
//                    try {
//                        messageController.messageAttendee(message, attendeeUsername);
//                    } catch (UserNotFoundException | RecipientNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
//                case MESSAGE_SPEAKER: {
//                    //messageSpeaker(String message, String speakerusername)
//                    this.oldPresenter.displayMessagingPrompt("aSpeaker");
//                    //String line1 = sc.nextLine();
//                    String speakerUsername = userInput.nextLine();
//                    //line1 = sc.nextLine();
//                    String message = userInput.nextLine();
//                    try {
//                        messageController.messageSpeaker(message, speakerUsername);
//                    } catch (UserNotFoundException | RecipientNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
//                case VIEW_CONVERSATION:
//                    try {
//                        Set<String> myConversations = cm.getAllUserConversationRecipients(username);
//                        if (myConversations.isEmpty()) {
//                            this.oldPresenter.displayConversations("empty", myConversations);
//                        } else {
//                            this.oldPresenter.displayConversations("non_empty", myConversations);
//                            String user = userInput.nextLine();
//                            int pastMessages = userInput.nextInt();
//                            userInput.nextLine();
//                            this.viewMessagesFrom(user, pastMessages);
//                        }
//                    } catch (InputMismatchException e) {
//                        this.oldPresenter.displayConversationsErrors("mismatch");
//                    } catch (ObjectNotFoundException e) {
//                        this.oldPresenter.displayConversationsErrors("no_user");
//                    } catch (NullPointerException e) {
//                        this.oldPresenter.displayConversationsErrors("no_conversation");
//                    }
//                    break;
//                case VIEW_SCHEDULE:
//                    this.SeeTalkSchedule();
//                    break;
//                case SIGNUP_EVENT: {
//                    this.oldPresenter.displayTalkPrompt("attend");
//                    String input = userInput.nextLine();
//                    if(isNumeric(input)){
//                        Integer id = Integer.parseInt(input);
//                        this.signupForTalk(id);}
//                    else{
//                        this.oldPresenter.displayTalkPrompt("invalid");
//                    }
//                    break;
//                }
//                case LEAVE_EVENT: {
//                    this.oldPresenter.displayTalkPrompt("cancel");
//                    String input = userInput.nextLine();
//                    if(isNumeric(input)){
//                        Integer id = Integer.parseInt(input);
//                        this.cancelSignupForTalk(id);}
//                    else{
//                        this.oldPresenter.displayTalkPrompt("invalid");
//                    }
//                    break;
//                }
//                case VIEW_MY_SCHEDULE:
//                    this.seeAttendeeTalkSchedule();
//                    break;
//                case DOWNLOAD_SCHEDULE: {  // download all event schedule in HTML
//                    this.oldPresenter.displayDownloadSchedulePrompt();
//                    command = userInput.nextLine();
//                    if (command.equalsIgnoreCase("Y")) {
//                        try {
//                            HTMLManager hm = new HTMLManager(em);
//                            hm.generateHTML();
//                            hm.openHTML();
//                            this.oldPresenter.displayPrompt("Successful! downloaded: " +
//                                    hm.getDownloadLocation());
//                        } catch (HTMLWriteErrorException e) { // wrong while processing HTML
//                            this.oldPresenter.displayPrompt("Internal Error during HTML processing");
//                        }
//                    } else {
//                        oldPresenter.displayPrompt("Aborted");
//                    }
//                    oldPresenter.displayPrompt("");
//                    break;
//                }
//                case VIEW_MENU:
//                    oldPresenter.displayAttendeeMenu();
//                    break;
//                default:
//                    oldPresenter.displayPrompt("Invalid input, please try again:\n");
//            }
//            if (loggedIn) {
//                oldPresenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
//                command = userInput.nextLine();
//
//                validinput = false;
//                while (!validinput) {
//                    for(AttendeeMenuEnum commandEnum: commandlist){
//                        if (commandEnum.stringValue.equals(command)) {
//                            validinput = true;
//                            enumRequest = commandEnum;
//                            break;
//                        }
//                    }
//                    if(!validinput){
//                        oldPresenter.displayPrompt("Invalid input, please try again:\n");
//                        oldPresenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
//                        command = userInput.nextLine();
//                    }
//                }
//            }
//        }
        return programEnd;
    }
}
