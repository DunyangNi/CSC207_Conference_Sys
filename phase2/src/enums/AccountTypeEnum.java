package enums;

/**
 * Represents an <code>Account</code> type identifier for menus and registration.
 */
public enum AccountTypeEnum {
    ATTENDEE("1"),
    SPEAKER("2"),
    ORGANIZER("3"),
    VIP_ATTENDEE("4"),
    INVALID(null);

    public final String stringValue;

    /**
     * Creates an <code>AccountTypeEnum</code> mapping a given string value.
     *
     * @param stringValue given string value
     */
    AccountTypeEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Returns an <code>AccountTypeEnum</code> mapping a given string value.
     *
     * @param stringValue given string value
     * @return <code>AccountTypeEnum</code> mapping a given string value
     */
    public static AccountTypeEnum fromString(String stringValue) {
        for (AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()) {
            if (accountTypeEnum.stringValue != null && accountTypeEnum.stringValue.equals(stringValue)) {
                return accountTypeEnum;
            }
        }
        return INVALID;
    }
}
