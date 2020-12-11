package views;

import controllers.account.AccountController;
import controllers.account.ContactController;
import controllers.account.LoginController;
import controllers.account.RegistrationController;
import controllers.event.DownloadScheduleController;
import controllers.event.EventController;
import controllers.event.LocationController;
import controllers.message.ConversationController;
import controllers.message.MessageController;
import controllers.request.RequestController;
import enums.ViewEnum;
import gateways.DataManager;
import presenters.StartPresenter;
import presenters.account.*;
import presenters.event.*;
import presenters.message.ConversationPresenter;
import presenters.message.MessagePresenter;
import presenters.request.PendingRequestsPresenter;
import presenters.request.RequestResolutionPresenter;
import presenters.request.RequestSendPresenter;
import presenters.request.ResolvedRequestsPresenter;
import views.account.*;
import views.event.*;
import views.message.*;
import views.request.PendingRequestsListView;
import views.request.RequestResolveView;
import views.request.RequestSendView;
import views.request.ResolvedRequestsListView;

public class ViewFactory {
    private final DataManager dm;

    public ViewFactory(DataManager dm) {
        this.dm = dm;
    }

    public View getView(ViewEnum viewEnum) {
        View view = null;

        switch (viewEnum) {
            // TODO Reduce amount of code either by extending ViewFactory into sub-classes for each Account type or by creating Controller + Presenter factories.
            // Start
            case LOGOUT:
            case START:
                view = new StartView(new StartPresenter());
                break;
            case REGISTRATION:
                view = new RegistrationView(new RegistrationController(dm), new RegistrationPresenter());
                break;
            case LOGIN:
                view = new LoginView(new LoginController(dm), new LoginPresenter());
                break;
            case ORGANIZER:
                view = new AccountView(new AccountController(dm), new OrganizerPresenter());
                break;
            case SPEAKER:
                view = new AccountView(new AccountController(dm), new SpeakerPresenter());
                break;
            case ATTENDEE:
                view = new AccountView(new AccountController(dm), new AttendeePresenter());
                break;
            case VIP_ATTENDEE:
                view = new AccountView(new AccountController(dm), new VipAttendeePresenter());
                break;

            // Account
            // TODO Consider removing these case
            case EXIT:
            case VIEW_ALL_ACCOUNTS:
            case INVALID:
            case VIEW_MENU:
            case VOID:
                break;

            case ADD_CONTACT:
                view = new ContactAddView(new ContactController(dm), new ContactPresenter());
                break;
            case REMOVE_CONTACT:
                view = new ContactRemoveView(new ContactController(dm), new ContactPresenter());
                break;
            case VIEW_CONTACTS:
                view = new ContactListView(new ContactController(dm), new ContactPresenter());
                break;
            case MESSAGE:
                view = new MessageView(new MessageController(dm), new MessagePresenter());
                break;
            case VIEW_CONVERSATION:
                view = new ConversationView(new ConversationController(dm), new ConversationPresenter());
                break;
            case VIEW_EVENT_SCHEDULE:
                view = new EventScheduleView(new EventController(dm), new EventPresenter());
                break;
            case DOWNLOAD_SCHEDULE:
                view = new DownloadScheduleView(new DownloadScheduleController(dm), new DownloadSchedulePresenter());
                break;

            // Organizer
            case CREATE_ACCOUNT:
                view = new AccountCreationView(new RegistrationController(dm), new RegistrationPresenter());
                break;
            case MESSAGE_ALL_SPEAKERS:
                view = new MessageAllSpeakersView(new MessageController(dm), new MessagePresenter());
                break;
            case MESSAGE_ALL_ATTENDEES:
                view = new MessageAllAttendeesView(new MessageController(dm), new MessagePresenter());
                break;
            case VIEW_LOCATIONS:
                view = new LocationListView(new LocationController(dm), new LocationPresenter());
                break;
            case ADD_LOCATION:
                view = new LocationAddView(new LocationController(dm), new LocationPresenter());
                break;
            case CREATE_EVENT:
                view = new EventCreationView(new EventController(dm), new EventCreationPresenter());
                break;
            case CANCEL_EVENT:
                view = new EventCancelView(new EventController(dm), new EventCancelPresenter());
                break;
            case RESCHEDULE_EVENT:
                view = new EventRescheduleView(new EventController(dm), new EventReschedulePresenter());
                break;
            case VIEW_UNRESOLVED_REQUEST:
                view = new PendingRequestsListView(new RequestController(dm), new PendingRequestsPresenter());
                break;
            case VIEW_RESOLVED_REQUEST:
                view = new ResolvedRequestsListView(new RequestController(dm), new ResolvedRequestsPresenter());
                break;
            case RESOLVE_REQUEST:
                view = new RequestResolveView(new RequestController(dm), new RequestResolutionPresenter());
                break;

            // Speaker
            case MESSAGE_TALK_ATTENDEES:
                view = new MessageTalkAttendeesView(new MessageController(dm), new MessagePresenter());
                break;
            case VIEW_SPEAKER_SCHEDULE:
                view = new SpeakerScheduleView(new EventController(dm), new EventPresenter());
                break;

            // Attendee
            case VIEW_SIGNUP_SCHEDULE:
                view = new AttendeeScheduleView(new EventController(dm), new EventPresenter());
                break;
            case SIGNUP_EVENT:
                view = new SignupView(new EventController(dm), new SignupPresenter());
                break;
            case LEAVE_EVENT:
                view = new SignupCancelView(new EventController(dm), new SignupCancelPresenter());
                break;
            case SEND_REQUEST:
                view = new RequestSendView(new RequestController(dm), new RequestSendPresenter());
                break;
        }
        return view;
    }
}
