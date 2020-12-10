package views.message;

import controllers.message.ConversationController;
import enums.ViewEnum;
import exceptions.NoMessagesException;
import exceptions.NonPositiveIntegerException;
import exceptions.not_found.MessageNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import presenters.message.ConversationPresenter;
import views.View;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class ConversationView implements View {
    private final ConversationPresenter presenter;
    private final ConversationController controller;
    private final Scanner userInput = new Scanner(System.in);

    public ConversationView(ConversationController controller, ConversationPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public ViewEnum runView() {
        try {
            Set<String> recipients = controller.getAllUserConversationRecipients();
            presenter.displayConversations(recipients);

            presenter.recipientPrompt();
            String recipient = userInput.nextLine();

            presenter.numMessagesPrompt();
            int numMessages = Integer.parseInt(userInput.nextLine());

            presenter.displayConversationMessages(controller.viewMessagesFrom(recipient, numMessages));
        } catch (InputMismatchException e){
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
