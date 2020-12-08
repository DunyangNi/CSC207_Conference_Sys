package views.event;

import controllers.event.LocationController;
import exceptions.NonPositiveIntegerException;
import exceptions.already_exists.ObjectAlreadyExistsException;
import gateways.DataManager;
import presenters.event.LocationPresenter;

import java.util.Scanner;

public class AddLocationView {

        private final LocationPresenter presenter = new LocationPresenter();
        private final LocationController controller;
        private final Scanner userInput = new Scanner(System.in);

        public AddLocationView(DataManager dm) {

            this.controller = new LocationController(dm);
        }

        public void runView() {
            presenter.addRoomPrompt();
            String location = userInput.nextLine();
            try {
                controller.addNewLocation(location);
            } catch (NonPositiveIntegerException | ObjectAlreadyExistsException e) {
                e.printStackTrace();
            }
        }

    }

