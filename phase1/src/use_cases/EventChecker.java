package use_cases;

import Throwables.ConflictException;
import entities.Event;
import entities.Talk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class EventChecker implements Serializable {

//    public boolean CheckTimeOverlap(Calendar time_1, Calendar time_2){
//        return time_1.compareTo(time_2) == 0;
//    }
//
//    public boolean isValidTalk(Calendar time, String location, ArrayList<String> locations, String speaker, ArrayList<Talk> talks, ArrayList<Event> events) {
//        // call general helper
//        if (isValidEvent(time, location, locations, events)) {
//            // TODO: 11/16/20 Prevent double-booking speaker
//            for(Talk talk: talks) {
//                if (talk.getSpeaker().equals(speaker) && CheckTimeOverlap(time, talk.getTime())) {
//                    return false; }
//            }
//            return true;
//        }
//        return false;
//    }
//
//    public boolean isValidEvent(Calendar time, String location, ArrayList<String> locations, ArrayList<Event> events) {
//        // check if location is valid
//        if (!locations.contains(location)) {
//            return false;
//        }
//        // check if time is valid
//        if (!(9 <= time.get(Calendar.HOUR_OF_DAY) && time.get(Calendar.HOUR_OF_DAY) <= 16)) {
//            return false;
//        }
//        // check if any conflicting events or events already existing
//        for(Event event: events) {
//            if (event.getLocation().equals(location) && event.getTime().equals(time)){
//                return false;
//            }
//        }
//        return true;
//    }

    public boolean isValidEvent(Calendar time, String location, ArrayList<String> locations, ArrayList<Event> events) throws ConflictException {
        if (!locations.contains(location)) {
            throw new ConflictException("Location");
        }
        if (!(9 <= time.get(Calendar.HOUR_OF_DAY) && time.get(Calendar.HOUR_OF_DAY) <= 16)) {
            throw new ConflictException("Time");
        }
        for (Event event : events) {
            if (event.getLocation().equals(location) && event.getTime().equals(time)) {
                throw new ConflictException("Location + Time");
            }
        }
        return true;
    }

    public boolean isValidTalk(Calendar time, String location, ArrayList<String> locations, String speaker, ArrayList<Talk> talks, ArrayList<Event> events) throws ConflictException {
        // TODO: 11/17/20 Prevent double booking a speaker
        // ^^ Implemented a fix. Please try if it works.
        for (Talk t : talks) {
            if (t.getSpeaker().equals(speaker) && t.getTime().equals(time))
                throw new ConflictException("Speaker + Time");
        }
        return isValidEvent(time, location, locations, events);
    }
}
