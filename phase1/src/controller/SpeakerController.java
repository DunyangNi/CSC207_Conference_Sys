package controller;
import entities.Speaker;
import use_cases.*;
import entities.*;
import java.util.*;
import java.lang.*;
import presenter.*;

public class SpeakerController {
    private String username;
    private EventManager eventmanager;
    private AccountManager accountmanager;
    private ConversationManager conversationManager;
    private Presenter presenter;

    public SpeakerController(String username, EventManager eventmanager,
                             AccountManager accountmanager, ConversationManager conversationManager) {
        this.username = username;
        this.eventmanager = eventmanager;
        this.accountmanager = accountmanager;
        this.conversationManager = conversationManager;
        this.presenter = new Presenter(eventmanager, accountmanager);
    }

    private Calendar timeoftalkrequesthelper(){
        boolean isDone = false;
        Calendar time = Calendar.getInstance();
        while (!isDone) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Specify the year of the talk");
                int year = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the month of the talk (1-12)");
                int month = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the day of the month of the talk (1-31)");
                int dayofmonth = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the hour of the day of the talk (0-23)");
                int hourofday = sc.nextInt();
                sc.nextLine();

                time.set(Calendar.YEAR, year);
                TimeZone tz = TimeZone.getTimeZone("America/New_York");
                time.setTimeZone(tz);
                time.set(Calendar.DAY_OF_MONTH, dayofmonth);
                time.set(Calendar.MONTH, month - 1);
                time.set(Calendar.HOUR_OF_DAY, hourofday);
                time.set(Calendar.MINUTE, 0);
                time.set(Calendar.SECOND, 0);
                time.set(Calendar.MILLISECOND, 0);
                isDone = true;
            }
            catch (Exception e){
                System.out.println("Invalid value! Try again\n");
            }
        }

        return time;
    }


    public void SeeSpeakerTalkSchedule() {
        this.presenter.displaySpeakerTalksSchedule(this.username);
    }

    public void messageAttendee(String message, String attendeeUsername) {
        conversationManager.sendMessage(this.username, attendeeUsername, message);
    }

    public void messageAttendeesAtTalks(ArrayList<String> topiclist, ArrayList<Calendar> timelist, String message) {
        ArrayList<String> seenAttendeeUsernames = new ArrayList<>();
        for(int i = 0; i <= topiclist.size() - 1; i++) {
            if(this.eventmanager.containsTalk(topiclist.get(i), timelist.get(i))) {
                ArrayList<String> attendeesAtTalk = this.eventmanager.getAttendeesAtEvent(topiclist.get(i), timelist.get(i));
                for(String attendeeUsername: attendeesAtTalk) {
                    if(!seenAttendeeUsernames.contains(attendeeUsername)) {
                        seenAttendeeUsernames.add(attendeeUsername);
                    }
                }
            }
        }

        for(String attendeeUsername: seenAttendeeUsernames) {
            conversationManager.sendMessage(this.username, attendeeUsername, message);
        }
    }

    public void SeeTalkSchedule() {
        this.presenter.displayTalkSchedule();
    }

    public void addFriend(String accountUsername) {
        FriendManager.AddFriend(this.accountmanager.fetchAccount(this.username), this.accountmanager.fetchAccount(accountUsername));
    }

    public void removeFriend(String accountUsername) {
        FriendManager.RemoveFriend(this.accountmanager.fetchAccount(this.username), this.accountmanager.fetchAccount(accountUsername));
    }

    public void seeFriendList() {
        this.presenter.displayFriendList(this.username);
    }

    public void viewMessagesFrom(String recipient, int numMessages) {
        if (numMessages < 0) {
            System.out.println("This is an invalid number");
        }
        else if (!conversationManager.getAllUserConversationRecipients(this.username).contains(recipient)) {
            System.out.println("Error: User '" + recipient + "' is not found");
            System.out.println();
        }
        else {
            String msgToPrint;
            ArrayList<Integer> selectedConversation = conversationManager.getConversationMessages(this.username, recipient);
            System.out.println("Your last " + numMessages + " messages with " + recipient + ":");
            System.out.println();
            int recent_num = Math.min(numMessages, selectedConversation.size());
            for (int i = 0; i < recent_num; i++) {
                msgToPrint = conversationManager.messageToString(selectedConversation.get(numMessages - recent_num - 1 + i));
                System.out.println(msgToPrint);
                System.out.println();
            }
        }
    }
    public void runSpeakerInteraction() {
        Scanner sc = new Scanner(System.in);
        boolean loop_on = true;
        while(loop_on){
            System.out.println("What would you like to do?");
            System.out.println("1 = see a schedule of talks you're giving");
            System.out.println("2 = message an attendee");
            System.out.println("3 = message all attendees for one or multiple talks you're giving");
            System.out.println("4 = see a schedule of all talks");
            System.out.println("5 = add a new contact");
            System.out.println("6 = remove a contact");
            System.out.println("7 = view contacts list");
            System.out.println("8 = view your conversation with someone");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                this.SeeSpeakerTalkSchedule();

            }
            else if(choice == 2) {
                Set<String> allAttendees = accountmanager.getAttendeelist().keySet();
                if (!allAttendees.isEmpty()) {
                    System.out.println("List of attendees");
                    System.out.println("---------------------------------------------");
                    for (String atd: allAttendees) {
                        System.out.println(atd);
                    }
                    System.out.println("---------------------------------------------\n");

                    // messageAttendee(String message, String attendeeusername)
                    System.out.println("Specify the attendee's username");
                    //String line1 = sc.nextLine();
                    String attendeeusername = sc.nextLine();
                    System.out.println("Specify the message to send");
                    //line1 = sc.nextLine();
                    String message = sc.nextLine();

                    if (allAttendees.contains(attendeeusername)) {
                        this.messageAttendee(message, attendeeusername);
                    }
                    else{
                        System.out.println("ERROR: Invalid user");
                    }
                }
                else{
                    System.out.println("No attendee to search");
                }

            }
            else if(choice == 3) {
                // messageAttendeesAtTalks(ArrayList<String> topiclist, ArrayList<Calendar> timelist, String message)
                ArrayList<String> topiclist = new ArrayList<>();
                ArrayList<Calendar> timelist = new ArrayList<>();
                boolean loop = true;
                while (loop) {
                    System.out.println("Specify the topic of a talk you're giving");
                    //String line1 = sc.nextLine();
                    String topic = sc.nextLine();
                    Calendar time = this.timeoftalkrequesthelper();

                    if(this.accountmanager.isTalkSpeaker(this.accountmanager.fetchSpeaker(this.username), this.eventmanager.fetchTalk(topic, time))) {
                        topiclist.add(topic);
                        timelist.add(time);
                    }
                    else {
                        System.out.println("You are not the speaker for that talk");
                    }
                    System.out.println("Would you like to add another talk? (1 = yes, 0 = no)");
                    int response = sc.nextInt();
                    sc.nextLine();
                    if(response == 0) {
                        loop = false;
                    }
                }
                System.out.println("Specify message to send");
                //String line1 = sc.nextLine();
                String message = sc.nextLine();
                this.messageAttendeesAtTalks(topiclist, timelist, message);


            }
            else if(choice == 4) {
                this.SeeTalkSchedule();
            }
            else if(choice == 5) {
                Set<String> allAccts = accountmanager.getAccountlist().keySet();
                if (!allAccts.isEmpty()) {
                    System.out.println("List of users");
                    System.out.println("---------------------------------------------");
                    for (String acct : allAccts) {
                        System.out.println(acct);
                    }
                    System.out.println("---------------------------------------------\n");
                    System.out.println("Specify username of contact to add");
                    //String line1 = sc.nextLine();
                    String friendusername = sc.nextLine();

                    if (allAccts.contains(friendusername)) {
                        this.addFriend(friendusername);
                    }
                    else{
                        System.out.println("ERROR: Invalid User");
                    }
                }
                else{
                    System.out.println("ERROR: No users");
                }

            }
            else if(choice == 6) {
                System.out.println("Specify username of contact to remove");
                //String line1 = sc.nextLine();
                String usernametoremove = sc.nextLine();

                Set<String> allAccts = accountmanager.getAccountlist().keySet();
                if (allAccts.contains(usernametoremove)) {
                    this.removeFriend(usernametoremove);
                }
                else {
                    System.out.println("ERROR: invalid user to remove");
                }

            }

            else if(choice == 7) {
                this.seeFriendList();

            }
            else if(choice == 8) {
                //viewMessagesFrom(String recipientUsername, int numMessages)
                Set<String> convUsersWithMe = conversationManager.getAllUserConversationRecipients(username);

                if (convUsersWithMe.isEmpty()) {
                    System.out.println("There is no conversion to search");
                }
                else {
                    System.out.println("List of users you have had conversation with");
                    System.out.println("---------------------------------------------");
                    for (String conUser : convUsersWithMe) {
                        System.out.println(conUser);
                    }
                    System.out.println("---------------------------------------------\n");

                    System.out.println("Specify username of user you would like to view your conversation with");
                    //String line1 = sc.nextLine();
                    String user = sc.nextLine();
                    System.out.println("How many past messages would you like to see?");
                    int pastmessages = sc.nextInt();
                    sc.nextLine();
                    this.viewMessagesFrom(user, pastmessages);
                }

            }

            System.out.println("Thank you. Would you like to do another task?");
            System.out.println("1 = yes, 0 = no");
            int response = sc.nextInt();
            sc.nextLine();
            if(response == 0) {
                loop_on = false;
            }
        }

    }

}
