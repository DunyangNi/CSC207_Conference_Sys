package views.account;

import controllers.account.AccountController;
import enums.AccountTypeEnum;
import enums.OrganizerMenuEnum;
import gateways.*;
import presenters.account.OrganizerPresenter;
import views.event.EventView;
import views.event.LocationView;
import views.message.ConversationView;
import views.message.MessageAllAttendeesView;
import views.message.MessageAllSpeakersView;
import views.message.MessageView;

import java.util.Scanner;

public class OrganizerView {
    private final DataManager dm;
    private final AccountController controller;
    private final OrganizerPresenter presenter = new OrganizerPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public OrganizerView(DataManager dm) {
        this.dm = dm;
        this.controller = new AccountController(dm);
    }

    public void viewOrganizerMenu() {
        boolean loggedIn = true;

        presenter.startPrompt();
        presenter.displayOrganizerMenu();
        presenter.requestCommandPrompt();

        while (loggedIn) {
            OrganizerMenuEnum enumCommand = OrganizerMenuEnum.fromString(userInput.nextLine());
            switch (enumCommand) {
                // TODO: 12/04/20 Enable exit
//                case EXIT:
//                    loggedIn = false;
//                    break;
                case LOGOUT:
                    loggedIn = false;
                    break;
                case NEW_SPEAKER:
                    RegistrationView registrationView = new RegistrationView(dm);
                    registrationView.accountInfoView(AccountTypeEnum.SPEAKER);
                    break;
                case VIEW_ALL_ACCOUNTS:
                    presenter.accountList(controller.getAllAccounts());
                    break;
                case ADD_CONTACT:
                    ContactView contactView = new ContactView(dm);
                    contactView.viewAddFriendMenu();
                    break;
                case REMOVE_CONTACT:
                    contactView = new ContactView(dm);
                    contactView.viewRemoveFriendMenu();
                    break;
                case VIEW_CONTACTS:
                    contactView = new ContactView(dm);
                    contactView.viewFriendList();
                    break;
                case MESSAGE:
                    MessageView messageView = new MessageView(dm);
                    messageView.runView();
                    break;
                case MESSAGE_ALL_SPEAKERS:
                    MessageAllSpeakersView messageAllSpeakersView = new MessageAllSpeakersView(dm);
                    messageAllSpeakersView.runView();
                    break;
                case MESSAGE_ALL_ATTENDEES:
                    MessageAllAttendeesView messageAllAttendeesView = new MessageAllAttendeesView(dm);
                    messageAllAttendeesView.runView();
                    break;
                case VIEW_CONVERSATION:
                    ConversationView conversationView = new ConversationView(dm);
                    conversationView.conversations();
                    break;
                case ADD_ROOM:
                    LocationView locationView = new LocationView(dm);
                    locationView.addRoom();
                    break;
                case VIEW_ROOMS:
                    locationView = new LocationView(dm);
                    locationView.rooms();
                    break;
                case ADD_EVENT:
                    EventView eventView = new EventView(dm);
                    eventView.eventCreation();
                    break;
                case VIEW_EVENT_SCHEDULE:
                    eventView = new EventView(dm);
                    eventView.allTalksSchedule();
                    break;
                case CANCEL_EVENT:
                    eventView = new EventView(dm);
                    eventView.eventCancellation();
                    break;
                case RESCHEDULE_EVENT:
                    eventView = new EventView(dm);
                    eventView.eventReschedule();
                    break;
                case VIEW_MENU:
                    presenter.displayOrganizerMenu();
                    break;
                case INVALID:
                    presenter.invalidInputPrompt();
            }
            controller.saveData();
            presenter.savedDataPrompt();

            if (loggedIn) {
                presenter.requestCommandPrompt();
            }
        }
    }
}
