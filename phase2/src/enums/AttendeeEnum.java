package enums;

public enum AttendeeEnum {
    EXIT("00"),
    LOGOUT("0"),
    VIEW_ALL_ACCOUNTS("1"),
    ADD_CONTACT("2"),
    REMOVE_CONTACT("3"),
    VIEW_CONTACTS("4"),
    MESSAGE_ATTENDEE("5"),
    MESSAGE_SPEAKER("6"),
    VIEW_CONVERSATION("7"),
    VIEW_SCHEDULE("8"),
    SIGNUP_EVENT("9"),
    LEAVE_EVENT("10"),
    VIEW_MY_SCHEDULE("11"),
    DOWNLOAD_SCHEDULE("12"),
    VIEW_MENU("*");

    public final String stringValue;

    AttendeeEnum(String stringValue) {
        this.stringValue = stringValue;
    }
}
