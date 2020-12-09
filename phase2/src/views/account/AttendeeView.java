package views.account;

import controllers.account.AccountController;
import enums.AttendeeMenuEnum;
import enums.ViewEnum;
import gateways.*;
import presenters.account.AttendeePresenter;
import views.View;
import views.event.*;
import views.message.ConversationView;
import views.message.MessageView;

import java.util.Scanner;

public class AttendeeView implements View {
    private final AccountController controller;
    private final AttendeePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public AttendeeView(AccountController controller, AttendeePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public ViewEnum runView() {
        boolean loggedIn = true;

        presenter.startPrompt();
        presenter.displayAttendeeMenu();

        while (loggedIn) {
            AttendeeMenuEnum enumCommand = AttendeeMenuEnum.fromString(userInput.nextLine());
//            switch (enumCommand) {
////                case EXIT:
////                    loggedIn = false;
////                    break;
//                case LOGOUT:
//                    loggedIn = false;
//                    break;
//                case VIEW_ALL_ACCOUNTS:
//                    presenter.displayAccountList(controller.getAccountList());
//                    break;
//                case ADD_CONTACT:
//                    ContactView contactView = new ContactView(dm);
//                    contactView.runView();
//                    break;
//                case REMOVE_CONTACT:
//                    contactView = new ContactView(dm);
//                    contactView.runView();
//                    break;
//                case VIEW_CONTACTS:
//                    contactView = new ContactView(dm);
//                    contactView.runView();
//                    break;
//                case MESSAGE:
//                    MessageView messageView = new MessageView(dm);
//                    messageView.runView();
//                    break;
//                case VIEW_CONVERSATION:
//                    ConversationView conversationView = new ConversationView(dm);
//                    conversationView.runView();
//                    break;
//                case VIEW_EVENT_SCHEDULE:
//                    AllTalksScheduleView allTalksScheduleView = new AllTalksScheduleView(dm);
//                    allTalksScheduleView.runView();
//                    break;
//                case VIEW_SIGNUP_SCHEDULE:
//                    AttendeeScheduleView attendeeScheduleView= new AttendeeScheduleView(dm);
//                    attendeeScheduleView.runView();
//                    break;
//                case SIGNUP_EVENT:
//                case LEAVE_EVENT:
//                    SignupView signupView = new SignupView(dm);
//                    signupView.runView(enumCommand);
//                    break;
//                case DOWNLOAD_SCHEDULE:
//                    DownloadScheduleView dlView = new DownloadScheduleView(dm);
//                    dlView.runView();
//                    break;
//                case VIEW_MENU:
//                    presenter.displayAttendeeMenu();
//                    break;
//                case INVALID:
//                    presenter.invalidInputPrompt();
//            }
            controller.saveData();

            if (loggedIn) {
                presenter.requestCommandPrompt();
            }
        }
        return ViewEnum.START;
    }
}
