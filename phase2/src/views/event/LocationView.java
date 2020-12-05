package views.event;

import controllers.event.LocationController;
import exceptions.NonPositiveIntegerException;
import exceptions.already_exists.ObjectAlreadyExistsException;
import presenters.event.LocationPresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

import java.util.Scanner;

public class LocationView {
    private final String username;
    private final AccountManager am;
    private final FriendManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final LocationPresenter presenter = new LocationPresenter();
    private final LocationController controller;
    private final Scanner userInput = new Scanner(System.in);

    public LocationView(String username, AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.username = username;
        this.am = am;
        this.fm = fm;
        this.cm = cm;
        this.em = em;
        this.controller = new LocationController(em);
    }

    public void addRoom() {
        presenter.addRoomPrompt();
        String location = userInput.nextLine();
        try {
            controller.addNewLocation(location);
        } catch (NonPositiveIntegerException | ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    public void rooms() {
        presenter.displayRooms(controller.getLocations());
    }
}
