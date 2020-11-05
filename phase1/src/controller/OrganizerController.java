package controller;
import entities.Speaker;
import use_cases.*;
import entities.*;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Calendar;
import java.util.TimeZone;

public class OrganizerController {
    private String username;
    private EventManager eventmanager;
    private AccountManager accountmanager;

    public OrganizerController(String username, EventManager eventmanager, AccountManager accountmanager) {
        this.username = username;
        this.eventmanager = eventmanager;
        this.accountmanager = accountmanager;
    }
    public void addNewLocation(String location) {
        this.eventmanager.addLocation(location);
    }
    public void createSpeakerAccount(String username, String password, String firstname, String lastname) {
        this.accountmanager.AddNewSpeaker(username, password, firstname, lastname);
    }

    public void scheduleSpeaker(String username, int year, int dayofmonth, int month, int hourofday, String topic, String location, String speakerusername) {
        //requires a method that allows you to query speakers based on username
        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, year);
        TimeZone tz = TimeZone.getTimeZone("EST");
        time.setTimeZone(tz);
        time.set(Calendar.DAY_OF_MONTH, dayofmonth);
        time.set(Calendar.MONTH, month - 1);
        time.set(Calendar.HOUR_OF_DAY, hourofday);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        this.eventmanager.AddNewEvent(topic, time, location, this.accountmanager.fetchOrganizer(this.username), this.accountmanager.fetchSpeaker(speakerusername));
    }
    public void cancelTalk(String topic) {
        //needs a method for encoding string as event
        this.eventmanager.cancelTalk(this.eventmanager.fetchTalk(topic));
    }

    public void rescheduleTalk(String topic, int year, int dayofmonth, int month, int hourofday) {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, year);
        TimeZone tz = TimeZone.getTimeZone("EST");
        time.setTimeZone(tz);
        time.set(Calendar.DAY_OF_MONTH, dayofmonth);
        time.set(Calendar.MONTH, month - 1);
        time.set(Calendar.HOUR_OF_DAY, hourofday);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        this.eventmanager.ChangeTime(this.eventmanager.fetchTalk(topic), time);
    }
    public void messageAllSpeakers(String message) {
        Iterator<Speaker> speakerIterator = this.accountmanager.speakerIterator();
        while( speakerIterator.hasNext()){
            ConversationManager.sendMessage(this.accountmanager.fetchOrganizer(this.username), speakerIterator.next(), message);
        }
    }

    public void messageSpeaker(String message, String speakerusername) {
        ConversationManager.sendMessage(this.accountmanager.fetchOrganizer(this.username), this.accountmanager.fetchSpeaker(speakerusername), message);
    }

    public void messageAllAttendees(String message) {
        Iterator<Attendee> attendeeIterator = this.accountmanager.attendeeIterator();
        while( attendeeIterator.hasNext()){
            ConversationManager.sendMessage(this.accountmanager.fetchOrganizer(this.username), attendeeIterator.next(), message);
        }
    }

    public void messageAttendee(String message, String attendeeusername) {
        ConversationManager.sendMessage(this.accountmanager.fetchOrganizer(this.username), this.accountmanager.fetchattendee(attendee), message);
    }

    public void runOrganizerInteraction() {
        Scanner sc = new Scanner(System.in);
        boolean loop_on = true:
        while loop_on {
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
            int choice = sc.nextInt();

            if(choice == 1){
                System.out.println("Specify location:");
                String line1 = sc.nextLine();
                String location = sc.nextLine();
                this.addNewLocation(location);
                System.out.println("Thank you. Would you like to do another task?");
                System.out.println("1 = yes, 0 = no");
                int response = sc.nextInt();
                if(response == 0) {
                    loop_on = false;
                }
            }
            elif(choice == 2) {
                //TODO
            }
        }
    }




}
