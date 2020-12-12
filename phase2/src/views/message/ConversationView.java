package views.message;

import controllers.message.ConversationController;
import enums.ViewEnum;
import exceptions.NoMessagesException;
import exceptions.NonPositiveIntegerException;
import exceptions.not_found.MessageNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import presenters.message.ConversationPresenter;
import views.factory.View;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * View responsible for viewing messages
 * Fields:
 * presenter: ConversationPresenter responsible for displaying relevant prompts
 * controller: ConversationController responsible for conversation functionality
 */

public class ConversationView implements View {
    private final ConversationPresenter presenter;
    private final ConversationController controller;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>ConversationView</code> based on the following parameters
     * @param controller The given ConversationController
     * @param presenter The given ConversationPresenter
     */

    public ConversationView(ConversationController controller, ConversationPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Runs the view.
     * @return ViewEnum.VOID
     */

    @Override
    public ViewEnum runView() {
        presenter.conversationHeader();
        try {
            Set<String> recipients = controller.getAllUserConversationRecipients();
            presenter.displayConversations(recipients);

            presenter.recipientPrompt();
            String recipient = userInput.nextLine();

            presenter.numMessagesPrompt();
            int numMessages = Integer.parseInt(userInput.nextLine());

            ArrayList<String> messages = controller.viewMessagesFrom(recipient, numMessages);
            if(!controller.contactable(recipient)){
                presenter.contactNotFoundNotification();
            }
            presenter.displayConversationMessages(recipient, messages);
        } catch (InputMismatchException e) {
            presenter.inputMismatchNotification();
        } catch (NonPositiveIntegerException | NumberFormatException e) {
            presenter.positiveNumberNotification();
        } catch (MessageNotFoundException e) {
            presenter.messageNotFoundNotification();
        } catch (RecipientNotFoundException e) {
            presenter.recipientNotFoundNotification();
        } catch (NoMessagesException e) {
            presenter.noMessagesNotification();
        }
        return ViewEnum.VOID;
    }
}
