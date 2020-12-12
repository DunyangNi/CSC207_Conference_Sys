package enums;

/**
 * Represents a VIP Attendee Menu item identifier.
 */
public enum VipAttendeeMenuEnum {
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
     * Creates an <code>VipAttendeeMenuEnum</code> mapping a given string value.
     *
     * @param stringValue given string value
     */
    VipAttendeeMenuEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Returns an <code>VipAttendeeMenuEnum</code> mapping a given string value.
     *
     * @param stringValue given string value
     * @return <code>VipAttendeeMenuEnum</code> mapping a given string value
     */
    public static VipAttendeeMenuEnum fromString(String stringValue) {
        for (VipAttendeeMenuEnum vipAttendeeMenuEnum : VipAttendeeMenuEnum.values()) {
            if (vipAttendeeMenuEnum.stringValue != null && vipAttendeeMenuEnum.stringValue.equals(stringValue)) {
                return vipAttendeeMenuEnum;
            }
        }
        return INVALID;
    }
}
