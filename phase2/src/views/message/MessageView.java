package views.message;

import deprecated.MessageController;
import enums.AttendeeMenuEnum;
import enums.OrganizerMenuEnum;
import enums.SpeakerMenuEnum;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import gateways.DataManager;
import presenters.message.MessagePresenter;
import use_cases.account.AccountManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import use_cases.account.ContactManager;

import java.util.ArrayList;
import java.util.Scanner;

public class MessageView {
    private final DataManager dm;
    private final String username;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final MessagePresenter presenter = new MessagePresenter();
    private final MessageController controller;
    private final Scanner userInput = new Scanner(System.in);

    public MessageView(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.username = dm.getUsername();
        this.controller = new MessageController(username, am, cm, em);
    }

    public void message(OrganizerMenuEnum accountType) {
        if (accountType.equals(OrganizerMenuEnum.MESSAGE_SPEAKER) || accountType.equals(OrganizerMenuEnum.MESSAGE_ATTENDEE)) {
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

    public void message(SpeakerMenuEnum accountType) {
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

    public void message(AttendeeMenuEnum accountType) {
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
