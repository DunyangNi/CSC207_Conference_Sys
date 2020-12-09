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
import enums.ViewEnum;
import gateways.DataManager;
import presenters.StartPresenter;
import presenters.account.*;
import presenters.event.*;
import presenters.message.ConversationPresenter;
import presenters.message.MessagePresenter;
import views.account.*;
import views.event.*;
import views.message.*;

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
                StartPresenter startPresenter = new StartPresenter();
                view = new StartView(startPresenter);
                break;
            case REGISTRATION:
                RegistrationController registrationController = new RegistrationController(dm);
                RegistrationPresenter registrationPresenter = new RegistrationPresenter();
                view = new RegistrationView(registrationController, registrationPresenter);
                break;
            case LOGIN:
                LoginController loginController = new LoginController(dm);
                LoginPresenter loginPresenter = new LoginPresenter();
                view = new LoginView(loginController, loginPresenter);
                break;
            case ORGANIZER:
                AccountController accountController = new AccountController(dm);
                AccountPresenter accountPresenter = new OrganizerPresenter();
                view = new AccountView(accountController, accountPresenter);
                break;
            case SPEAKER:
                accountController = new AccountController(dm);
                accountPresenter = new SpeakerPresenter();
                view = new AccountView(accountController, accountPresenter);
                break;
            case ATTENDEE:
            case VIP: // TODO Decide how to implement this
                accountController = new AccountController(dm);
                accountPresenter = new AttendeePresenter();
                view = new AccountView(accountController, accountPresenter);
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
                ContactController contactController = new ContactController(dm);
                ContactPresenter contactPresenter = new ContactPresenter();
                view = new ContactAddView(contactController, contactPresenter);
                break;
            case REMOVE_CONTACT:
                contactController = new ContactController(dm);
                contactPresenter = new ContactPresenter();
                view = new ContactRemoveView(contactController, contactPresenter);
                break;
            case VIEW_CONTACTS:
                contactController = new ContactController(dm);
                contactPresenter = new ContactPresenter();
                view = new ContactListView(contactController, contactPresenter);
                break;
            case MESSAGE:
                MessageController messageController = new MessageController(dm);
                MessagePresenter messagePresenter = new MessagePresenter();
                view = new MessageView(messageController, messagePresenter);
                break;
            case VIEW_CONVERSATION:
                ConversationController conversationController = new ConversationController(dm);
                ConversationPresenter conversationPresenter = new ConversationPresenter();
                view = new ConversationView(conversationController, conversationPresenter);
                break;
            case VIEW_EVENT_SCHEDULE:
                EventController eventController = new EventController(dm);
                EventPresenter eventPresenter = new EventPresenter();
                view = new EventScheduleView(eventController, eventPresenter);
                break;
            case DOWNLOAD_SCHEDULE:
                DownloadScheduleController downloadScheduleController = new DownloadScheduleController(dm);
                DownloadSchedulePresenter downloadSchedulePresenter = new DownloadSchedulePresenter();
                view = new DownloadScheduleView(downloadScheduleController, downloadSchedulePresenter);
                break;

            // Organizer
            case NEW_SPEAKER:
                registrationController = new RegistrationController(dm);
                registrationPresenter = new RegistrationPresenter();
                view = new AccountCreationView(registrationController, registrationPresenter);
                break;
            case MESSAGE_ALL_SPEAKERS:
                messageController = new MessageController(dm);
                messagePresenter = new MessagePresenter();
                view = new MessageAllSpeakersView(messageController, messagePresenter);
                break;
            case MESSAGE_ALL_ATTENDEES:
                messageController = new MessageController(dm);
                messagePresenter = new MessagePresenter();
                view = new MessageAllAttendeesView(messageController, messagePresenter);
                break;
            case VIEW_LOCATIONS:
                LocationController locationController = new LocationController(dm);
                LocationPresenter locationPresenter = new LocationPresenter();
                view = new LocationListView(locationController, locationPresenter);
                break;
            case ADD_LOCATION:
                locationController = new LocationController(dm);
                locationPresenter = new LocationPresenter();
                view = new LocationAddView(locationController, locationPresenter);
                break;
            case ADD_EVENT:
                eventController = new EventController(dm);
                EventCreationPresenter eventCreationPresenter = new EventCreationPresenter();
                view = new EventCreationView(eventController, eventCreationPresenter);
                break;
            case CANCEL_EVENT:
                eventController = new EventController(dm);
                EventCancelPresenter eventCancelPresenter = new EventCancelPresenter();
                view = new EventCancelView(eventController, eventCancelPresenter);
                break;
            case RESCHEDULE_EVENT:
                eventController = new EventController(dm);
                EventReschedulePresenter eventReschedulePresenter = new EventReschedulePresenter();
                view = new EventRescheduleView(eventController, eventReschedulePresenter);
                break;

            // Speaker
            case MESSAGE_TALK_ATTENDEES:
                messageController = new MessageController(dm);
                messagePresenter = new MessagePresenter();
                view = new MessageTalkAttendeesView(messageController, messagePresenter);
                break;
            case VIEW_SPEAKER_SCHEDULE:
                eventController = new EventController(dm);
                eventPresenter = new EventPresenter();
                view = new SpeakerScheduleView(eventController, eventPresenter);
                break;

            // Attendee
            case VIEW_SIGNUP_SCHEDULE:
                eventController = new EventController(dm);
                eventPresenter = new EventPresenter();
                view = new AttendeeScheduleView(eventController, eventPresenter);
                break;
            case SIGNUP_EVENT:
                eventController = new EventController(dm);
                SignupPresenter signupPresenter = new SignupPresenter();
                view = new SignupView(eventController, signupPresenter);
                break;
            case LEAVE_EVENT:
                eventController = new EventController(dm);
                SignupCancelPresenter signupCancelPresenter = new SignupCancelPresenter();
                view = new SignupCancelView(eventController, signupCancelPresenter);
                break;
        }
        return view;
    }
}
