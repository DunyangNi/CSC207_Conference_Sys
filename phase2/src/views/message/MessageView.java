package views.message;

import controllers.message.MessageController;
import enums.AttendeeEnum;
import enums.OrganizerEnum;
import enums.SpeakerEnum;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import presenters.message.MessagePresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.ContactManager;

import java.util.ArrayList;
import java.util.Scanner;

public class MessageView {
    private final String username;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final MessagePresenter presenter = new MessagePresenter();
    private final MessageController controller;
    private final Scanner userInput = new Scanner(System.in);

    public MessageView(String username, AccountManager am, ContactManager fm, ConversationManager cm, EventManager em) {
        this.username = username;
        this.am = am;
        this.fm = fm;
        this.cm = cm;
        this.em = em;
        this.controller = new MessageController(username, am, cm, em);
    }

    public void message(OrganizerEnum accountType) {
        if (accountType.equals(OrganizerEnum.MESSAGE_SPEAKER) || accountType.equals(OrganizerEnum.MESSAGE_ATTENDEE)) {
            presenter.usernamePrompt();
            String username = userInput.nextLine();
        }
        presenter.messagePrompt();
        String message = userInput.nextLine();
        try {
            switch (accountType) {
            case MESSAGE_SPEAKER:
                controller.messageSpeaker(username, message);
                break;
            case MESSAGE_ATTENDEE:
                controller.messageAttendee(username, message);
                break;
            case MESSAGE_ALL_SPEAKERS:
                controller.messageAllSpeakers(message);
                break;
            case MESSAGE_ALL_ATTENDEES:
                controller.messageAllAttendees(message);
                break;
            }
        }
        catch (UserNotFoundException | RecipientNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void message(SpeakerEnum accountType) {
        try {
            switch (accountType) {
                case MESSAGE_ATTENDEE:
                    presenter.usernamePrompt();
                    String username = userInput.nextLine();
                    presenter.messagePrompt();
                    String message = userInput.nextLine();
                    controller.messageAttendee(username, message);
                    break;
                case MESSAGE_ALL_AT_TALKS:
                    ArrayList<Integer> selectedSpeakerTalks = new ArrayList<>();
                    presenter.eventIdPrompt();
                    Integer id = Integer.parseInt(userInput.nextLine());
                    if (em.isSpeakerOfTalk(id, this.username)) {
                        selectedSpeakerTalks.add(id);
                    }
                    presenter.messagePrompt();
                    message = userInput.nextLine();
                    controller.messageAttendeesAtTalks(selectedSpeakerTalks, message);
                    break;
            }
        }
        catch (UserNotFoundException | RecipientNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void message(AttendeeEnum accountType) {
        presenter.usernamePrompt();
        String username = userInput.nextLine();
        presenter.messagePrompt();
        String message = userInput.nextLine();
        try {
            controller.messageAttendee(username, message);
        }
        catch (UserNotFoundException | RecipientNotFoundException e) {
            e.printStackTrace();
        }
    }
}
