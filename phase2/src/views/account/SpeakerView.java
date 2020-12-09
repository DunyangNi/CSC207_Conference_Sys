package views.account;

import controllers.account.AccountController;
import enums.SpeakerMenuEnum;
import enums.ViewEnum;
import presenters.account.AccountPresenter;
import views.View;

import java.util.Scanner;

public class SpeakerView implements View {
    private final AccountController controller;
    private final AccountPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public SpeakerView(AccountController controller, AccountPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public ViewEnum runView() {
        presenter.startPrompt();
        presenter.displayUserMenu();
        presenter.requestCommandPrompt();

        boolean loggedIn = true;
        while (loggedIn) {
            SpeakerMenuEnum speakerMenuEnum = SpeakerMenuEnum.fromString(userInput.nextLine());
            switch (speakerMenuEnum) {
                case EXIT:
                    return ViewEnum.EXIT;
                case LOGOUT:
                    loggedIn = false;
                    break;
                case VIEW_ALL_ACCOUNTS:
                    presenter.displayAccountList(controller.getAccountList());
                    break;
                case VIEW_MENU:
                    presenter.displayUserMenu();
                    break;
                case INVALID:
                    presenter.invalidInputPrompt();
                    break;
                default:
                    ViewEnum viewEnum = ViewEnum.valueOf(speakerMenuEnum.name());
                    controller.getView(viewEnum).runView();
                    break;
            }
//            switch (speakerMenuEnum) {
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
            controller.saveData(); // TODO Consider moving this to ConferenceSystem
            presenter.savedDataPrompt();
            presenter.requestCommandPrompt();
        }
        return ViewEnum.LOGOUT;
    }
}