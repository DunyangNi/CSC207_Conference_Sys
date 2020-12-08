package views.account;

import controllers.account.AccountController;
import enums.AttendeeMenuEnum;
import gateways.*;
import presenters.account.AttendeePresenter;
import views.event.EventView;
import views.message.ConversationView;
import views.event.SignupView;
import views.message.MessageView;

import java.util.Scanner;

public class AttendeeView {
    private final DataManager dm;
    private final AccountController controller;
    private final AttendeePresenter presenter = new AttendeePresenter();
    private final Scanner userInput = new Scanner(System.in);

    public AttendeeView(DataManager dm) {
        this.dm = dm;
        this.controller = new AccountController(dm);
    }

    public void viewAttendeeMenu() {
        boolean loggedIn = true;

        presenter.startPrompt();
        presenter.displayAttendeeMenu();

        while (loggedIn) {
            AttendeeMenuEnum enumCommand = AttendeeMenuEnum.fromString(userInput.nextLine());
            switch (enumCommand) {
                // TODO: 12/04/20 Enable exit
//                case EXIT:
//                    loggedIn = false;
//                    break;
                case LOGOUT:
                    loggedIn = false;
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
                case VIEW_CONVERSATION:
                    ConversationView conversationView = new ConversationView(dm);
                    conversationView.conversations();
                    break;
                case VIEW_EVENT_SCHEDULE:
                    EventView eventView = new EventView(dm);
                    eventView.allTalksSchedule();
                    break;
                case VIEW_SIGNUP_SCHEDULE:
                    eventView = new EventView(dm);
                    eventView.attendeeSchedule();
                    break;
                case SIGNUP_EVENT:
                case LEAVE_EVENT:
                    SignupView signupView = new SignupView(dm);
                    signupView.runView(enumCommand);
                    break;
                case DOWNLOAD_SCHEDULE:
                    // TODO: 12/06/20 Finish implementing this operation
                    break;
                case VIEW_MENU:
                    presenter.displayAttendeeMenu();
                    break;
                case INVALID:
                    presenter.invalidInputPrompt();
            }
            controller.saveData();

            if (loggedIn) {
                presenter.requestCommandPrompt();
            }
        }
    }
}
