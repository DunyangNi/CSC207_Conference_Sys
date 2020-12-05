package enums;

public enum OrganizerCommand {
    EXIT("00"),
    LOGOUT("0"),
    NEW_SPEAKER("1"),
    VIEW_ALL_ACCOUNTS("2"),
    ADD_CONTACT("3"),
    REMOVE_CONTACT("4"),
    VIEW_CONTACTS("5"),
    MESSAGE_SPEAKER("6"),
    MESSAGE_ATTENDEE("7"),
    MESSAGE_ALL_SPEAKERS("8"),
    MESSAGE_ALL_ATTENDEES("9"),
    VIEW_CONVERSATION("10"),
    ADD_ROOM("11"),
    VIEW_ROOMS("12"),
    ADD_EVENT("13"),
    CANCEL_EVENT("14"),
    RESCHEDULE_EVENT("15"),
    VIEW_SCHEDULE("16"),
    VIEW_MENU("*");

    public final String stringValue;

    OrganizerCommand(String command) {
        this.stringValue = command;
    }
}
