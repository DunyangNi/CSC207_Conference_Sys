package enums;

public enum AttendeeMenuEnum {
    // TODO: Update numbering after all extensions are implemented
    EXIT("00"),
    LOGOUT("0"),
    VIEW_ALL_ACCOUNTS("1"),
    ADD_CONTACT("2"),
    REMOVE_CONTACT("3"),
    VIEW_CONTACTS("4"),
    MESSAGE("5"),
    VIEW_CONVERSATION("6"),
    VIEW_EVENT_SCHEDULE("7"),
    DOWNLOAD_SCHEDULE("8"),
    VIEW_SIGNUP_SCHEDULE("9"),
    SIGNUP_EVENT("10"),
    LEAVE_EVENT("11"),
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
