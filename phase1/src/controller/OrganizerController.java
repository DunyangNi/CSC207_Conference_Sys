package controller;

import use_cases.*;
import presenter.*;
import java.util.*;
import java.lang.*;

public class OrganizerController extends UserController{
    // timeoftalkrequesthelper() is now deprecated due to use of IDs.
    private AccountManager accountmanager;
    public OrganizerController(String username, EventManager eventmanager, AccountManager accountmanager,
                               ConversationManager conversationManager, FriendManager friendManager) {
        super(username, eventmanager,conversationManager, friendManager);
        this.accountmanager = accountmanager;
    }
    public void addNewLocation(String location) {
        this.eventmanager.addLocation(location);
    }

    public void createSpeakerAccount(String username, String password, String firstname, String lastname) {
        this.accountmanager.AddNewSpeaker(username, password, firstname, lastname);
    }


    public void scheduleSpeaker(int year, int day, int month, int hour, String topic, String location, String speaker) {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, year);
        TimeZone tz = TimeZone.getTimeZone("America/New_York");
        time.setTimeZone(tz);
        time.set(Calendar.DAY_OF_MONTH, day);
        time.set(Calendar.MONTH, month - 1);
        time.set(Calendar.HOUR_OF_DAY, hour);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        this.eventmanager.AddNewEvent(topic, time, location, username, speaker);
    }

    public void cancelTalk(Integer id) { this.eventmanager.cancelTalk(id); }

    public void rescheduleTalk(Integer id, int newYear, int newDay, int newMonth, int newHour) {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, newYear);
        TimeZone tz = TimeZone.getTimeZone("America/New_York");
        time.setTimeZone(tz);
        time.set(Calendar.DAY_OF_MONTH, newDay);
        time.set(Calendar.MONTH, newMonth - 1);
        time.set(Calendar.HOUR_OF_DAY, newHour);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        this.eventmanager.ChangeTime(id, time);
    }
    public void messageAllSpeakers(String message) {
        Iterator<String> speakerusernameiterator = this.accountmanager.speakerUsernameIterator();
        while( speakerusernameiterator.hasNext()){
            conversationManager.sendMessage(this.username, speakerusernameiterator.next(), message);
        }
    }

    public void messageSpeaker(String message, String speaker) {
        conversationManager.sendMessage(this.username, speaker, message);
    }

    public void messageAllAttendees(String message) {
        Iterator<String> attendeeusernameiterator = this.accountmanager.attendeeUsernameIterator();
        while(attendeeusernameiterator.hasNext()){
            conversationManager.sendMessage(this.username, attendeeusernameiterator.next(), message);
        }
    }

    public void messageAttendee(String message, String attendeeUsername) {
        conversationManager.sendMessage(this.username, attendeeUsername, message);
    }

    public void seeLocationList() {
        ArrayList<String> locations = this.eventmanager.fetchLocations();
        System.out.println("Locations:\n");
        for (String location : locations) { System.out.println(location); }
    }

    @Override
    public void runInteraction() {
        Scanner sc = new Scanner(System.in);
        boolean loop_on = true;
        while(loop_on){
            System.out.println("What would you like to do?");
            System.out.println("1 = enter a room into the system");
            System.out.println("2 = create a new speaker account");
            System.out.println("3 = schedule a speaker for a talk");
            System.out.println("4 = cancel an event");
            System.out.println("5 = reschedule an event");
            System.out.println("6 = message all speakers");
            System.out.println("7 = message an individual speaker");
            System.out.println("8 = message all attendees");
            System.out.println("9 = message an individual attendee");
            System.out.println("10 = see talk schedule");
            System.out.println("11 = add a contact");
            System.out.println("12 = remove a contact");
            System.out.println("13 = view contacts list");
            System.out.println("14 = view your conversation with someone");
            System.out.println("15 = see the list of rooms");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1){
                System.out.print("Please enter name of new location: ");
                String location = sc.nextLine();
                this.addNewLocation(location);
            }

            else if (choice == 2) {
                System.out.println("Specify account username"); String username = sc.nextLine();
                System.out.println("Specify account password"); String password = sc.nextLine();
                System.out.println("What is speaker's first name?"); String firstname = sc.nextLine();
                System.out.println("What is speaker's last name?"); String lastname = sc.nextLine();
                this.createSpeakerAccount(username, password, firstname, lastname);
            }

            else if(choice == 3) {
                System.out.println("Specify the username of talk speaker"); String username = sc.nextLine();
                System.out.println("Specify the location of the talk"); String location = sc.nextLine();
                System.out.println("Specify the topic of the talk"); String topic = sc.nextLine();
                System.out.println("Specify the year of the talk"); int year = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the numerical month for this talk (1-12)"); int month = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the date of the month for this talk (1-31)"); int day = sc.nextInt();
                sc.nextLine();
                // the schedule only goes from 9-5 !
                System.out.println("Specify the hour of the day for this talk (0-23)"); int hour = sc.nextInt();
                sc.nextLine();
                this.scheduleSpeaker(year, day, month, hour, topic, location, username);
            }

            else if(choice == 4) {
                System.out.print("Please enter the ID of a talk you wish to cancel: ");
                int id = sc.nextInt();
                this.cancelTalk(id);
            }

            else if(choice == 5) {
                System.out.println("Please enter the ID of a talk you wish to reschedule: ");
                int id = sc.nextInt();
                System.out.println("Specify the new year you would like to reschedule this talk to");
                int year = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the new month you would like to reschedule to (1-12)");
                int month = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the new day of the month you would like to reschedule to (1-31)");
                int day = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the new hour of the day you would like to reschedule to (0-23)");
                int hour = sc.nextInt();
                sc.nextLine();
                this.rescheduleTalk(id, year, day, month, hour);
            }

            else if(choice == 6) {
                System.out.println("Please enter the message that you want to send to all speakers:");
                String message = sc.nextLine();
                this.messageAllSpeakers(message);
            }

            else if(choice == 7) {
                System.out.print("Please enter the username of the speaker you wish to message: ");
                String username = sc.nextLine();
                System.out.println("Please enter the message you want to send to this speaker:");
                String message = sc.nextLine();
                this.messageSpeaker(message, username);
            }

            else if(choice == 8) {
                System.out.println("Please enter the message that you want to send to all attendees:");
                String message = sc.nextLine();
                this.messageAllAttendees(message);
            }

            else if(choice == 9) {
                System.out.print("Please enter the username of the attendee you want to message: ");
                String username = sc.nextLine();
                System.out.println("Please enter the message you want to send the attendee:");
                String message = sc.nextLine();
                this.messageAttendee(message, username);
            }

            else if(choice == 10) {
                this.SeeTalkSchedule();
            }

            else if(choice == 11) {
                System.out.print("Please enter the username of a contact to add: ");
                String contactToAdd = sc.nextLine();
                this.addFriend(contactToAdd);

            }

            else if(choice == 12) {
                System.out.print("Please enter the username of a contact to remove: ");
                String contactToRemove = sc.nextLine();
                this.removeFriend(contactToRemove);

            }

            else if(choice == 13) { this.seeFriendList(); }

            else if(choice == 14) {
                Set<String> myConversations = conversationManager.getAllUserConversationRecipients(username);
                if (myConversations.isEmpty()) { System.out.println("(No conversations)"); }
                else {
                    System.out.println("List of Conversation Recipients");
                    System.out.println("---------------------------------------------");
                    for (String recipient : myConversations) { System.out.println(recipient); }
                    System.out.println("---------------------------------------------\n");
                    System.out.print("To access a conversation, please enter the recipient's username: ");
                    String user = sc.nextLine();
                    System.out.println("How many past messages would you like to see?");
                    int pastMessages = sc.nextInt(); sc.nextLine();
                    this.viewMessagesFrom(user, pastMessages);
                }
            }

            else if(choice == 15) {
                this.seeLocationList();

            }
            System.out.println("Thank you. Would you like to do another task?");
            System.out.println("1 = yes, 0 = no");
            int response = sc.nextInt();
            sc.nextLine();
            if (response == 0) {
                loop_on = false;
            }

        }
    }
}





