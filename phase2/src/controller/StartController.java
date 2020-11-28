package controller;

import use_cases.*;
import views.LoginView;
import views.RegistrationView;

public class StartController {
    private final AccountManager am;
    private final FriendManager fm;
    private final ConversationManager cm;
    private final EventManager em;

    /**
     * Manages the home screen
     * @param am manages account data
     * @param fm manages friendList functionality
     * @param cm manages messaging functionality
     * @param em manages event data
     */
    public StartController(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.am = am;
        this.fm = fm;
        this.cm = cm;
        this.em = em;
    }

    public boolean start(String command) {
       if ("1".equals(command)) {
            LoginView loginView = new LoginView(am, fm, cm, em);
            return loginView.viewLoginMenu();
        }
       else if ("2".equals(command)) {
            RegistrationView registrationView = new RegistrationView(am, fm, cm, em);
            return registrationView.viewRegistrationMenu();
        }
       else {
           return true;
       }
    }

//    /**
//     * Displays the home/start menu to the user when they start the program or return to
//     * home screen.
//     * The user has the option to exit the program, login to an account, or register a new account.
//     * Should the user access their account, they are directed to a menu of options depending on their
//     * account type (organizer, speaker, or attendee)
//     * @return True if the user wishes to terminate the program
//     */
//    public boolean runStartMenu() {
//        presenter.preUserInput();
//        String command = input.nextLine();
//        boolean programEnd;
//        while (!(command.equals("0") || command.equals("1") || (command.equals("2")))) {
//            presenter.postUserInput();
//            command = input.nextLine();
//        }
//        if (command.equals("0")) {
//            programEnd = true;
//        } else if (command.equals("1")) {
//            LoginController loginController = new LoginController(accountManager, friendManager, conversationManager, eventManager);
//            programEnd = loginController.attemptLogin();
//        } else {
//            RegistrationController registrationController = new RegistrationController(accountManager, friendManager, conversationManager, eventManager);
//            programEnd = registrationController.attemptRegister();
//        }
//        return programEnd;
//    }
}