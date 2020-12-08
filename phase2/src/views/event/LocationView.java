package views.event;

import controllers.event.LocationController;
import exceptions.NonPositiveIntegerException;
import exceptions.already_exists.ObjectAlreadyExistsException;
import gateways.DataManager;
import presenters.event.LocationPresenter;
import use_cases.account.AccountManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import use_cases.account.ContactManager;

import java.util.Scanner;

public class LocationView {

    private final LocationPresenter presenter = new LocationPresenter();
    private final LocationController controller;
    private final Scanner userInput = new Scanner(System.in);

    public LocationView(DataManager dm) {

        this.controller = new LocationController(dm);
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
