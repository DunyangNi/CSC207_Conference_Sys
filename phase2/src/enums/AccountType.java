package enums;

public enum AccountType {
    ATTENDEE("1"),
    SPEAKER("2"),
    ORGANIZER("3");

    public final String type;

    AccountType(String type) {
        this.type = type;
    }
}
