package enums;

public enum OrganizerMenuEnum {
    // TODO: 12/07/20 Update numbering
    EXIT("00"),
    LOGOUT("0"),
    NEW_SPEAKER("1"),
    VIEW_ALL_ACCOUNTS("2"),
    ADD_CONTACT("3"),
    REMOVE_CONTACT("4"),
    VIEW_CONTACTS("5"),
    MESSAGE("6"),
    MESSAGE_ALL_SPEAKERS("8"),
    MESSAGE_ALL_ATTENDEES("9"),
    VIEW_CONVERSATION("10"),
    VIEW_EVENT_SCHEDULE("16"),
    VIEW_ROOMS("12"),
    ADD_ROOM("11"),
    ADD_EVENT("13"),
    CANCEL_EVENT("14"),
    RESCHEDULE_EVENT("15"),
    VIEW_MENU("*"),
    INVALID(null);

    public final String stringValue;

    OrganizerMenuEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    public static OrganizerMenuEnum fromString(String stringValue) {
        for (OrganizerMenuEnum organizerEnum : OrganizerMenuEnum.values()) {
            if (organizerEnum.stringValue != null && organizerEnum.stringValue.equals(stringValue)) {
                return organizerEnum;
            }
        }
        return INVALID;
    }
}
