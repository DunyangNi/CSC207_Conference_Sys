package enums;

public enum AttendeeCommand {
    EXIT("00"),
    LOGOUT("0"),
    ADD_CONTACT("1"),
    REMOVE_CONTACT("2"),
    VIEW_CONTACTS("3"),
    MESSAGE_ATTENDEE("4"),
    MESSAGE_SPEAKER("5"),
    VIEW_CONVERSATION("6"),
    VIEW_SCHEDULE("7"),
    SIGNUP_EVENT("8"),
    LEAVE_EVENT("9"),
    VIEW_MY_SCHEDULE("10"),
    DOWNLOAD_SCHEDULE("16"),
    VIEW_MENU("*");

    public final String command;

    AttendeeCommand(String command) {
        this.command = command;
    }
}
