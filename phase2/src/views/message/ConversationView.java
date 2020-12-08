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
    private final ConversationPresenter presenter;
    private final ConversationController controller;
    private final Scanner userInput = new Scanner(System.in);

    public ConversationView(ConversationController controller, ConversationPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public void conversations() {
        try {
            Set<String> recipients = controller.getAllUserConversationRecipients();
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
