package enums;

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
    VIEW_MENU("*"),
    INVALID(null);

    public final String stringValue;

    SpeakerMenuEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    public static SpeakerMenuEnum fromString(String stringValue) {
        for (SpeakerMenuEnum speakerMenuEnum : SpeakerMenuEnum.values()) {
            if (speakerMenuEnum.stringValue != null && speakerMenuEnum.stringValue.equals(stringValue)) {
                return speakerMenuEnum;
            }
        }
        return INVALID;
    }
}
