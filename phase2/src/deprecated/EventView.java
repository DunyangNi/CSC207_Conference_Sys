package deprecated;

import controllers.event.EventController;
import enums.EventTypeEnum;
import exceptions.InvalidEventTypeException;
import exceptions.InvalidTimeException;
import exceptions.PastTimeException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.LocationNotFoundException;
import gateways.DataManager;
import presenters.event.EventPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class EventView {
    private final String username;
    private final EventController controller;
    private final EventPresenter presenter = new EventPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public EventView(DataManager dm) {
        this.username = dm.getUsername();
        this.controller = new EventController(dm);
    }

    public void eventReschedule() {//replaced
        presenter.eventIdPrompt();
        int id = userInput.nextInt();
        Calendar time = collectTimeInfo();

        try {
            controller.rescheduleTalk(id, time);
        } catch (LocationInUseException | PastTimeException | SpeakerIsBusyException | LocationNotFoundException | InvalidTimeException | EventNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void attendeeSchedule() {//replaced
        presenter.myEventsPrompt();
        presenter.displayTalkSchedule(controller.getAttendeeEvents());
    }

    public void allTalksSchedule() {//replaced
        presenter.allEventsPrompt();
        presenter.displayTalkSchedule(controller.getAllEvents());
    }

    private Calendar collectTimeInfo() {
        presenter.timePrompt("day");
        int day = userInput.nextInt();
        presenter.timePrompt("month");
        int month = userInput.nextInt()-1;
        presenter.timePrompt("year");
        int year = userInput.nextInt();
        presenter.timePrompt("hour");
        int hour = userInput.nextInt();

        Calendar time = Calendar.getInstance();
        try{
            time.set(year, month, day, hour, 0, 0);
            time.clear(Calendar.MILLISECOND);}
        catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }
}