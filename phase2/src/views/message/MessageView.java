package views.message;

import controllers.message.MessageController;
import enums.ViewEnum;
import exceptions.not_found.AccountNotFoundException;
import gateways.DataManager;
import presenters.message.MessagePresenter;
import views.View;

import java.util.Scanner;

public class MessageView implements View {
    private final MessageController controller;
    private final MessagePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public MessageView(MessageController controller, MessagePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.usernamePrompt();
        String username = userInput.nextLine();
        presenter.messagePrompt();
        String message = userInput.nextLine();
        try {
            controller.messageAccount(username, message);
        } catch (AccountNotFoundException e) {
            presenter.AccountNotFoundPrompt();
        }
        return ViewEnum.VOID;
    }

//    public void message(OrganizerMenuEnum accountType) {
//        String username = null;
//        if (accountType.equals(OrganizerMenuEnum.MESSAGE_SPEAKER) || accountType.equals(OrganizerMenuEnum.MESSAGE_ATTENDEE)) {
//            presenter.usernamePrompt();
//            username = userInput.nextLine();
//        }
//        presenter.messagePrompt();
//        String message = userInput.nextLine();
//        try {
//            switch (accountType) {
//                case MESSAGE_SPEAKER:
//                    controller.messageSpeaker(username, message);
//                    break;
//                case MESSAGE_ATTENDEE:
//                    controller.messageAttendee(username, message);
//                    break;
//                case MESSAGE_ALL_SPEAKERS:
//                    controller.messageAllSpeakers(message);
//                    break;
//                case MESSAGE_ALL_ATTENDEES:
//                    controller.messageAllAttendees(message);
//                    break;
//            }
//        }
//        catch (UserNotFoundException | RecipientNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

//    public void message(SpeakerMenuEnum accountType) {
//        try {
//            switch (accountType) {
//                case MESSAGE_ATTENDEE:
//                    presenter.usernamePrompt();
//                    String username = userInput.nextLine();
//                    presenter.messagePrompt();
//                    String message = userInput.nextLine();
//                    controller.messageAttendee(username, message);
//                    break;
//                case MESSAGE_ALL_AT_TALKS:
//                    ArrayList<Integer> selectedSpeakerTalks = new ArrayList<>();
//                    presenter.idPrompt();
//                    Integer id = Integer.parseInt(userInput.nextLine());
//                    if (em.isSpeakerOfTalk(id, this.username)) {
//                        selectedSpeakerTalks.add(id);
//                    }
//                    presenter.messagePrompt();
//                    message = userInput.nextLine();
//                    controller.messageTalkAttendees(selectedSpeakerTalks, message);
//                    break;
//            }
//        }
//        catch (UserNotFoundException | RecipientNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

//    public void message(AttendeeMenuEnum accountType) {
//        presenter.usernamePrompt();
//        String username = userInput.nextLine();
//        presenter.messagePrompt();
//        String message = userInput.nextLine();
//        try {
//            controller.messageAttendee(username, message);
//        }
//        catch (UserNotFoundException | RecipientNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
