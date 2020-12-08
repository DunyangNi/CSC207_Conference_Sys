package views.event;

import controllers.event.LocationController;
import exceptions.NonPositiveIntegerException;
import exceptions.already_exists.ObjectAlreadyExistsException;
import gateways.DataManager;
import presenters.event.LocationPresenter;

import java.util.Scanner;

public class AddLocationView {

        private final LocationPresenter presenter;
        private final LocationController controller;
        private final Scanner userInput = new Scanner(System.in);

        public AddLocationView(LocationController controller, LocationPresenter presenter) {

            this.controller = controller;
            this.presenter = presenter;

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

