package deprecated;

import gateway.DataManager;
import use_cases.*;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.event.EventManager;

public class StartController {
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;

    /**
     * Manages the home screen
     * @param am manages account data
     * @param fm manages friendList functionality
     * @param cm manages messaging functionality
     * @param em manages event data
     */
    public StartController(DataManager dm) {
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
    }

//    public StartCommand start(String command) {
////       if ("1".equals(command)) {
////            LoginView loginView = new LoginView(am, fm, cm, em);
////            return loginView.runView();
////        }
////       else if ("2".equals(command)) {
////            RegistrationView registrationView = new RegistrationView(am, fm, cm, em);
////            return registrationView.runView();
////        }
////       else {
////           return true;
////       }
//
//        if (command.equals("1")) {
//            return StartCommand.LOGIN;
//        } else {
//            return StartCommand.REGISTER;
//        }
//    }

//    /**
//     * Displays the home/start menu to the user when they start the program or return to
//     * home screen.
//     * The user has the option to exit the program, login to an account, or register a new account.
//     * Should the user access their account, they are directed to a menu of options depending on their
//     * account type (organizer, speaker, or attendee)
//     * @return True if the user wishes to terminate the program
//     */
//    public boolean runStartMenu() {
//        presenters.preUserInput();
//        String command = input.nextLine();
//        boolean programEnd;
//        while (!(command.equals("0") || command.equals("1") || (command.equals("2")))) {
//            presenters.postUserInput();
//            command = input.nextLine();
//        }
//        if (command.equals("0")) {
//            programEnd = true;
//        } else if (command.equals("1")) {
//            LoginController loginController = new LoginController(accountManager, contactManager, conversationManager, eventManager);
//            programEnd = loginController.attemptLogin();
//        } else {
//            RegistrationController registrationController = new RegistrationController(accountManager, contactManager, conversationManager, eventManager);
//            programEnd = registrationController.attemptRegister();
//        }
//        return programEnd;
//    }
}