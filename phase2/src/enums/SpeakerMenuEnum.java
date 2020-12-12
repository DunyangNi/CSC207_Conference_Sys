package enums;

/**
 * Represents a Speaker Menu item identifier.
 */
public enum SpeakerMenuEnum {
    EXIT("00"),
    LOGOUT("0"),
    VIEW_ALL_ACCOUNTS("1"),
    ADD_CONTACT("2"),
    REMOVE_CONTACT("3"),
    VIEW_CONTACTS("4"),
    MESSAGE("5"),
    MESSAGE_TALK_ATTENDEES("6"),
    VIEW_CONVERSATION("7"),
    VIEW_EVENT_SCHEDULE("8"),
    VIEW_SPEAKER_SCHEDULE("9"),
    DOWNLOAD_SCHEDULE("10"),
    VIEW_MENU("*"),
    INVALID(null);

    public final String stringValue;

    /**
     * Creates an <code>SpeakerMenuEnum</code> mapping a given string value.
     *
     * @param stringValue given string value
     */
    SpeakerMenuEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Returns an <code>SpeakerMenuEnum</code> mapping a given string value.
     *
     * @param stringValue given string value
     * @return <code>SpeakerMenuEnum</code> mapping a given string value
     */
    public static SpeakerMenuEnum fromString(String stringValue) {
        for (SpeakerMenuEnum speakerMenuEnum : SpeakerMenuEnum.values()) {
            if (speakerMenuEnum.stringValue != null && speakerMenuEnum.stringValue.equals(stringValue)) {
                return speakerMenuEnum;
            }
        }
        return INVALID;
    }
}
