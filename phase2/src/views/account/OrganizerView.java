package views.account;

import controllers.account.AccountController;
import enums.OrganizerMenuEnum;
import enums.ViewEnum;
import presenters.account.OrganizerPresenter;
import views.View;

import java.util.Scanner;

public class OrganizerView implements View {
    private final AccountController controller;
    private final OrganizerPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public OrganizerView(AccountController controller, OrganizerPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public ViewEnum runView() {
        presenter.startPrompt();
        presenter.displayOrganizerMenu();
        presenter.requestCommandPrompt();

        boolean loggedIn = true;
        while (loggedIn) {
            OrganizerMenuEnum organizerMenuEnum = OrganizerMenuEnum.fromString(userInput.nextLine());
            switch (organizerMenuEnum) {
                case EXIT:
                    return null;
                case LOGOUT:
                    loggedIn = false;
                    break;
                case VIEW_ALL_ACCOUNTS:
                    presenter.displayAccountList(controller.getAccountList());
                    break;
                case VIEW_CONTACTS:
                    presenter.displayContactList(controller.getContactList());
                    break;
                case VIEW_MENU:
                    presenter.displayOrganizerMenu();
                    break;
                case INVALID:
                    presenter.invalidInputPrompt();
                default:
                    ViewEnum viewEnum = ViewEnum.valueOf(organizerMenuEnum.toString());
                    controller.getView(viewEnum).runView();
                    break;
            }

//            switch (organizerMenuEnum) {
////                case EXIT:
////                    loggedIn = false;
////                    break;
//                case LOGOUT:
//                    loggedIn = false;
//                    break;
//                case NEW_SPEAKER:
//                    RegistrationView registrationView = new RegistrationView(dm);
//                    registrationView.accountInfoView(AccountTypeEnum.SPEAKER);
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
//                case MESSAGE_ALL_SPEAKERS:
//                    MessageAllSpeakersView messageAllSpeakersView = new MessageAllSpeakersView(dm);
//                    messageAllSpeakersView.runView();
//                    break;
//                case MESSAGE_ALL_ATTENDEES:
//                    MessageAllAttendeesView messageAllAttendeesView = new MessageAllAttendeesView(dm);
//                    messageAllAttendeesView.runView();
//                    break;
//                case VIEW_CONVERSATION:
//                    ConversationView conversationView = new ConversationView(dm);
//                    conversationView.runView();
//                    break;
//                case ADD_ROOM:
//                    AddLocationView addLocationView = new AddLocationView(dm);
//                    addLocationView.runView();
//                    break;
//                case VIEW_ROOMS:
//                    AllLocationsView allLocationsView = new AllLocationsView(dm);
//                    allLocationsView.runView();
//                    break;
//                case ADD_EVENT:
//                    EventCreationView eventCreationView = new EventCreationView(dm);
//                    eventCreationView.runView();
//                    break;
//                case VIEW_EVENT_SCHEDULE:
//                    AllTalksScheduleView allTalksScheduleView = new AllTalksScheduleView(dm);
//                    allTalksScheduleView.runView();
//                    break;
//                case CANCEL_EVENT:
//                    EventCancelView eventCancelView = new EventCancelView(dm);
//                    eventCancelView.runView();
//                    break;
//                case RESCHEDULE_EVENT:
//                    EventRescheduleView eventRescheduleView= new EventRescheduleView(dm);
//                    eventRescheduleView.runView();
//                    break;
//                case VIEW_MENU:
//                    presenter.displayOrganizerMenu();
//                    break;
//                case INVALID:
//                    presenter.invalidInputPrompt();
//            }

            controller.saveData(); // TODO Consider moving this to ConferenceSystem
            presenter.savedDataPrompt();
            presenter.requestCommandPrompt();
        }
        return ViewEnum.START;
    }
}
