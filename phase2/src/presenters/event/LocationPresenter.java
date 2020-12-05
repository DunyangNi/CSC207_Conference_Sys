package presenters.event;

import presenters.ConsolePresenter;

import java.util.ArrayList;

public class LocationPresenter implements ConsolePresenter {
    @Override
    public void startPrompt() {
    }

    public void addRoomPrompt() {
        System.out.println("Enter a name for the new room:");
    }

    public void displayRooms(ArrayList<String> rooms) {
        for (String room : rooms) {
            System.out.println(room);
        }
    }

    @Override
    public void exitPrompt() {

    }
}
