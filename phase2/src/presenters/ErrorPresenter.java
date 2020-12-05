package presenters;

public class ErrorPresenter {

    public void EventIsFullPrompt(){
        System.out.println("This event is already full.");
    }

    public void EventNotFoundPrompt(){
        System.out.println("Could not find an event with the given ID.");
    }

    public void IntegerOutOfBoundsPrompt(){
        System.out.println("Integer out of bound.");
    }

    public void InvalidEventTypePrompt(){
        System.out.println("This is an invalid event type.");
    }

    public void InvalidTimePrompt(){
        System.out.println("This is an invalid time.");
    }

    public void LocationConfliftPrompt(){
        System.out.println("This causes a location to be double booked.");
    }

    public void LocationNotFoundPrompt(){
        System.out.println("Could not find such a location.");
    }

    public void NoMessagesPrompt(){
        System.out.println("No messages!.");
    }

    public void NoRecipientsPrompt(){
        System.out.println("No recipients!.");
    }

    public void ObjectAlreadyExistsPrompt(){
        System.out.println("This already exists!.");
    }

    public void ObjectNotFoundExistsPrompt(){
        System.out.println("Could not find such an object.");
    }

    public void PastTimePrompt(){
        System.out.println("This time is already passed.");
    }

    public void SpeakerConflictPrompt(){
        System.out.println("This causes a speaker to be double booked.");
    }

    public void TimeConflictPrompt(){
        System.out.println("This causes a time conflict.");
    }

    public void TypeConflictPrompt(){
        System.out.println("This causes a type conflict.");
    }

    public void UserNameNotFoundPrompt(){
        System.out.println("Could not find an account with this username.");
    }

    public void UserNotFoundPrompt(){
        System.out.println("Could not find such an account.");
    }
}
