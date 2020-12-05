package deprecated;

import presenter.ConsolePresenter;

public class EventRegistrationPresenter implements ConsolePresenter {

    @Override
    public void startPrompt() {
        System.out.println("You need to enter the speaker's username, room, topic, and the time");
    }

    @Override
    public void exitPrompt() {
        System.out.println("Registered event successfully");
    }

    public void usernamePrompt(){
        System.out.println("Enter the speaker's username:");
    }

    public void topicPrompt(){
        System.out.println("Enter the event topic:");
    }

    public void roomPrompt(){
        System.out.println("Enter the event room:");
    }

    public void timePrompt(){
        System.out.println("Enter the event time:");
    }



}
