package enums;

public enum AccountTypeEnum {
    ATTENDEE("1"),
    SPEAKER("2"),
    ORGANIZER("3");

    public final String type;

    AccountTypeEnum(String type) {
        this.type = type;
    }
}
