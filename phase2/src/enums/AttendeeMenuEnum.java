package enums;

/**
 * Represents a Attendee Menu item identifier.
 */
public enum AttendeeMenuEnum {
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
    SEND_REQUEST("12"),
    VIEW_MENU("*"),
    INVALID(null);

    public final String stringValue;

    /**
     * Creates an <code>AttendeeMenuEnum</code> mapping a given string value.
     *
     * @param stringValue given string value
     */
    AttendeeMenuEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Returns an <code>AttendeeMenuEnum</code> mapping a given string value.
     *
     * @param stringValue given string value
     * @return <code>AttendeeMenuEnum</code> mapping a given string value
     */
    public static AttendeeMenuEnum fromString(String stringValue) {
        for (AttendeeMenuEnum attendeeMenuEnum : AttendeeMenuEnum.values()) {
            if (attendeeMenuEnum.stringValue != null && attendeeMenuEnum.stringValue.equals(stringValue)) {
                return attendeeMenuEnum;
            }
        }
        return INVALID;
    }
}
