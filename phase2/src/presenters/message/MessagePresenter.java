package presenters.message;

import presenters.interfaces.EventErrorPresenter;
import presenters.interfaces.InputErrorPresenter;

public class MessagePresenter implements InputErrorPresenter, EventErrorPresenter {
    public void messageHeader() { System.out.println("\n[MESSAGE]");}

    public void messageSuccessNotification() { System.out.println("{Message sent.}"); }

    public void messageEventAttendeesHeader() { System.out.println("\n[MESSAGE YOUR ATTENDEES]");}

    public void messageEventAttendeesSuccessNotification() { System.out.println("{Successfully messaged attendees.}"); }

    public void messageEventAttendeesFailureNotification() { System.out.println("{Messaging attendees cancelled.}"); }

    public void messageAllSpeakersHeader() { System.out.println("\n[MESSAGE ALL SPEAKERS]"); }

    public void messageAllSpeakersSuccessNotification() { System.out.println("{Successfully messaged all speakers.}"); }

    public void messageAllSpeakersFailureNotification() { System.out.println("{Could not message all speakers.}"); }

    public void messageAllAttendeesHeader() { System.out.println("\n[MESSAGE ALL ATTENDEES]"); }

    public void messageAllAttendeesSuccessNotification() { System.out.println("{Successfully messaged all attendees.}"); }

    public void messageAllAttendeesFailureNotification() { System.out.println("{Could not message all attendees.}"); }

    public void recipientPrompt() {
        System.out.println("Please enter the username of the user you wish to message:");
    }

    public void messagePrompt() {
        System.out.println("Please enter the message you want to send:");
    }

    public void eventIDPrompt() {
        System.out.println("Please enter the IDs of your events on separate lines:");
        System.out.println("(Press ENTER/RETURN twice to finish)");
    }

    public void recipientNotFoundNotification() { System.out.println("{Sorry, the recipient could not be found.}");}

    public void contactNotFoundNotification() { System.out.println("{Sorry, the recipient is not in your contact list.}"); }

    public void noRecipientsNotification() { System.out.println("{Sorry, there are no recipients to message.}");}
}
