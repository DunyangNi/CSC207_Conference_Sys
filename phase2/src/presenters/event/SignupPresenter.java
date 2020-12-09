package presenters.event;

import presenters.Presenter;

public class SignupPresenter implements Presenter {
    @Override
    public void startPrompt() {
    }

    public void eventIdPrompt() {
        System.out.println("Please enter the ID of the Talk");
    }

    public void VipRestrictionPrompt() { System.out.println("Signup failed. This event is restricted for VIPs.");}

    public void EventNotFoundPrompt() { System.out.println("Could not find this event. Please enter a valid event ID.");}

    public void EventIsFullPrompt() { System.out.println("Signup failed. This event is already full.");}

    public void AlreadySignedUpPrompt() { System.out.println("Signup failed. You are already signed up.");}

    public void AttendeeNotFoundPrompt() { System.out.println("Signup failed. Could not find this attendee.");}

    @Override
    public void exitPrompt() {
    }
}
