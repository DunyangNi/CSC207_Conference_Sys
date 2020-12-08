package views.event;

import controllers.event.EventController;
import exceptions.InvalidTimeException;
import exceptions.PastTimeException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.LocationNotFoundException;
import gateways.DataManager;
import presenters.event.EventPresenter;
import presenters.event.TimePresenter;

import java.util.Calendar;
import java.util.Scanner;

public class EventRescheduleView {
    private final EventController controller;
    private final EventPresenter presenter = new EventPresenter();
    private final Scanner userInput = new Scanner(System.in);
    private final TimeGetterView timeGetter= new TimeGetterView(new TimePresenter());

    public EventRescheduleView(DataManager dm) {
        this.controller = new EventController(dm);
    }

    public void runView() {
        boolean valid = false;
        int id = -1;
        while(!valid) {
            try {
                presenter.eventIdPrompt();
                id = userInput.nextInt();
            } catch (Exception e) {
                presenter.GeneralInvalid();
            }
        }

        valid = false;
        Calendar time = Calendar.getInstance();
        while (!valid){
            try {
                time = timeGetter.collectTimeInfo();
                valid = true;
            } catch (Exception e) { timeGetter.errorMessage(); }
        }

        try {
            controller.rescheduleTalk(id, time);
        } catch (LocationInUseException | PastTimeException | SpeakerIsBusyException | LocationNotFoundException | InvalidTimeException | EventNotFoundException e) {
            e.printStackTrace();
        }
    }
}
