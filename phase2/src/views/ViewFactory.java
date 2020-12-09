package views;

import controllers.account.AccountController;
import controllers.account.ContactController;
import controllers.account.RegistrationController;
import controllers.account.LoginController;
import controllers.message.ConversationController;
import controllers.message.MessageController;
import enums.ViewEnum;
import gateways.DataManager;
import presenters.StartPresenter;
import presenters.account.*;
import presenters.message.ConversationPresenter;
import presenters.message.MessagePresenter;
import views.account.*;
import views.message.ConversationView;
import views.message.MessageAllAttendeesView;
import views.message.MessageAllSpeakersView;
import views.message.MessageView;

public class ViewFactory {
    private final DataManager dm;
    public ViewFactory(DataManager dm) {
        this.dm = dm;
    }

    public View getView(ViewEnum viewEnum) {
        View view = null;
        switch (viewEnum) {
            // Start
            case START:
            case LOGOUT:
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
                OrganizerPresenter organizerPresenter = new OrganizerPresenter();
                view = new OrganizerView(accountController, organizerPresenter);
                break;
            case SPEAKER:
                accountController = new AccountController(dm);
                SpeakerPresenter speakerPresenter = new SpeakerPresenter();
                view = new SpeakerView(accountController, speakerPresenter);
                break;
            case VIP: // TODO Implement this
                break;
            case ATTENDEE:
                accountController = new AccountController(dm);
                AttendeePresenter attendeePresenter = new AttendeePresenter();
                view = new AttendeeView(accountController, attendeePresenter);
                break;

            // Account
            case EXIT:
                break;
            case VIEW_ALL_ACCOUNTS: // TODO Choose how to implement this
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
            case VIEW_CONTACTS: // TODO Choose how to implement this
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
            case VIEW_EVENT_SCHEDULE: // TODO Choose how to implement this
                break;
            case DOWNLOAD_SCHEDULE:
                break;
            case VIEW_MENU: // TODO Choose how to implement this
                break;
            case INVALID: // TODO Choose how to implement this
                break;

            // Organizer
            case VOID:
                break;
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
            // TODO Implement the following:
            case ADD_ROOM:
                break;
            case VIEW_ROOMS:
                break;
            case ADD_EVENT:
                break;
            case CANCEL_EVENT:
                break;
            case RESCHEDULE_EVENT:
                break;

            // Speaker
            case MESSAGE_TALK_ATTENDEES:
                break;
            case VIEW_SPEAKER_SCHEDULE: // TODO Choose how to implement this
                break;

            // Attendee
            case VIEW_SIGNUP_SCHEDULE: // TODO Choose how to implement this
                break;
            case SIGNUP_EVENT:
                break;
            case LEAVE_EVENT:
                break;
        }
        return view;
    }
}
