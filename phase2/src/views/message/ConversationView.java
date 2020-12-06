package views.message;

import controllers.message.ConversationController;
import exceptions.NoMessagesException;
import exceptions.NonPositiveIntegerException;
import exceptions.not_found.ObjectNotFoundException;
import gateways.DataManager;
import presenters.message.ConversationPresenter;
import use_cases.account.AccountManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import use_cases.account.ContactManager;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class ConversationView {
    private final DataManager dm;
    private final String username;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final ConversationPresenter presenter = new ConversationPresenter();
    private final ConversationController controller;
    private final Scanner userInput = new Scanner(System.in);

    public ConversationView(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.username = dm.getUsername();
        this.controller = new ConversationController(dm);
    }

    public void conversations() {
        try {
            Set<String> recipients = cm.getAllUserConversationRecipients(username);
            presenter.conversationsPrompt(recipients);

            presenter.usernamePrompt();
            String recipient = userInput.nextLine();

            presenter.numMessagesPrompt();
            int numMessages = Integer.parseInt(userInput.nextLine());

            presenter.conversationMessages(controller.viewMessagesFrom(recipient, numMessages));

        } catch (InputMismatchException | ObjectNotFoundException | NullPointerException | NonPositiveIntegerException | NoMessagesException e) {
            e.printStackTrace();
        }
    }
}
