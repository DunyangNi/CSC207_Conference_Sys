package views.account;

import controllers.account.AccountController;
import entities.account.Speaker;
import enums.SpeakerMenuEnum;
import enums.ViewEnum;
import gateways.*;
import presenters.account.SpeakerPresenter;
import views.View;
import views.event.AllTalksScheduleView;
import views.message.ConversationView;
import views.message.MessageTalkAttendeesView;
import views.message.MessageView;

import java.util.Scanner;

public class SpeakerView implements View {
    private final AccountController controller;
    private final SpeakerPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public SpeakerView(AccountController controller, SpeakerPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public ViewEnum runView() {
        boolean loggedIn = true;

        presenter.startPrompt();
        presenter.displaySpeakerMenu();

        while (loggedIn) {
            SpeakerMenuEnum enumCommand = SpeakerMenuEnum.fromString(userInput.nextLine());
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
//                case MESSAGE_TALK_ATTENDEES:
//                    MessageTalkAttendeesView messageTalkAttendeesView = new MessageTalkAttendeesView(dm);
//                    messageTalkAttendeesView.runView();
//                    break;
//                case VIEW_CONVERSATION:
//                    ConversationView conversationView = new ConversationView(dm);
//                    conversationView.runView();
//                    break;
//                case VIEW_EVENT_SCHEDULE:
//                    AllTalksScheduleView allTalksScheduleView = new AllTalksScheduleView(dm);
//                    allTalksScheduleView.runView();
//                    break;
//                case VIEW_MENU:
//                    presenter.displaySpeakerMenu();
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
