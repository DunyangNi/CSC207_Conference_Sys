package enums;

public enum AttendeeMenuEnum {
    EXIT("00"),
    LOGOUT("0"),
    VIEW_ALL_ACCOUNTS("1"),
    ADD_CONTACT("2"),
    REMOVE_CONTACT("3"),
    VIEW_CONTACTS("4"),
    MESSAGE("5"), // TODO: Update numbering
    VIEW_CONVERSATION("6"),
    VIEW_EVENT_SCHEDULE("7"),
    VIEW_SIGNUP_SCHEDULE("8"),
    SIGNUP_EVENT("9"),
    LEAVE_EVENT("10"),
    DOWNLOAD_SCHEDULE("11"),
    VIEW_MENU("*"),
    INVALID(null);

    public final String stringValue;

    AttendeeMenuEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    public static AttendeeMenuEnum fromString(String stringValue) {
        for (AttendeeMenuEnum attendeeMenuEnum : AttendeeMenuEnum.values()) {
            if (attendeeMenuEnum.stringValue != null && attendeeMenuEnum.stringValue.equals(stringValue)) {
                return attendeeMenuEnum;
            }
        }
        return INVALID;
    }
}
