package enums;

public enum SpeakerEnum {
    EXIT("00"),
    LOGOUT("0"),
    VIEW_ALL_ACCOUNTS("1"),
    ADD_CONTACT("2"),
    REMOVE_CONTACT("3"),
    VIEW_CONTACTS("4"),
    MESSAGE_ATTENDEE("5"),
    MESSAGE_ALL_AT_TALKS("6"),
    VIEW_CONVERSATION("7"),
    MY_TALK_SCHEDULE("8"),
    VIEW_SCHEDULE("9"),
    VIEW_MENU("*");

    public final String stringValue;

    SpeakerEnum(String stringValue) {
        this.stringValue = stringValue;
    }
}
