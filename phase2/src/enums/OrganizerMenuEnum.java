package enums;

/**
 * Represents a Organizer Menu item identifier.
 */
public enum OrganizerMenuEnum {
    EXIT("00"),
    LOGOUT("0"),
    CREATE_ACCOUNT("1"),
    VIEW_ALL_ACCOUNTS("2"),
    ADD_CONTACT("3"),
    REMOVE_CONTACT("4"),
    VIEW_CONTACTS("5"),
    MESSAGE("6"),
    MESSAGE_ALL_SPEAKERS("7"),
    MESSAGE_ALL_ATTENDEES("8"),
    VIEW_CONVERSATION("9"),
    VIEW_EVENT_SCHEDULE("10"),
    VIEW_LOCATIONS("11"),
    ADD_LOCATION("12"),
    CREATE_EVENT("13"),
    CANCEL_EVENT("14"),
    RESCHEDULE_EVENT("15"),
    DOWNLOAD_SCHEDULE("16"),
    VIEW_UNRESOLVED_REQUEST("17"),
    VIEW_RESOLVED_REQUEST("18"),
    RESOLVE_REQUEST("19"),
    VIEW_MENU("*"),
    INVALID(null);

    public final String stringValue;

    /**
     * Creates an <code>OrganizerMenuEnum</code> mapping a given string value.
     *
     * @param stringValue given string value
     */
    OrganizerMenuEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Returns an <code>OrganizerMenuEnum</code> mapping a given string value.
     *
     * @param stringValue given string value
     * @return <code>OrganizerMenuEnum</code> mapping a given string value
     */
    public static OrganizerMenuEnum fromString(String stringValue) {
        for (OrganizerMenuEnum organizerEnum : OrganizerMenuEnum.values()) {
            if (organizerEnum.stringValue != null && organizerEnum.stringValue.equals(stringValue)) {
                return organizerEnum;
            }
        }
        return INVALID;
    }
}
