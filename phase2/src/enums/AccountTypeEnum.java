package enums;

public enum AccountTypeEnum {
    ATTENDEE("1"),
    SPEAKER("2"),
    ORGANIZER("3"),
    INVALID(null);

    public final String stringValue;

    AccountTypeEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    public static AccountTypeEnum fromString(String stringValue) {
        for (AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()) {
            if (accountTypeEnum.stringValue != null && accountTypeEnum.stringValue.equals(stringValue)) {
                return accountTypeEnum;
            }
        }
        return INVALID;
    }
}
