package presenters.event;

import presenters.InputErrorPresenter;
import presenters.Presenter;

public class EventReschedulePresenter implements Presenter, InputErrorPresenter, EventErrorPresenter {
    @Override
    public void startPrompt() {
        System.out.println("[EVENT RESCHEDULING]\nWelcome to Event Rescheduling! Follow the prompts to reschedule your event.");
    }

    @Override
    public void exitPrompt() {
        System.out.println("Event Rescheduling successful! Thank you! :)");
    }

    public void eventIDPrompt() { System.out.println("Please enter the ID of an event to reschedule: "); }

    public void invalidIDPrompt() {
        System.out.println("The ID entered could not be recognized. Ensure the input is a non-negative number.");
    }

    public void cancelExitPrompt() { System.out.println("Event Rescheduling cancelled. :("); }
}
