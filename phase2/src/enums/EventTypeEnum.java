package enums;

/**
 * Represents an Event type identifier for event creation.
 */
public enum EventTypeEnum {
    GENERAL_EVENT("0"),
    NETWORKING_EVENT("1"),
    TALK("2"),
    PANEL_DISCUSSION("3"),
    INVALID(null);

    public final String type;

    /**
     * Creates an <code>EventTypeEnum</code> mapping a given string option.
     *
     * @param type given string of option
     */
    EventTypeEnum(String type) {
        this.type = type;
    }

    /**
     * Returns an <code>EventTypeEnum</code> mapping a given string value.
     *
     * @param stringVal given string value
     * @return <code>AttendeeMenuEnum</code> mapping a given string value
     */
    public static EventTypeEnum fromString(String stringVal) {
        for (EventTypeEnum e : EventTypeEnum.values()) {
            if (e.type != null && e.type.equals(stringVal))
                return e;
        }
        return INVALID;
    }
}

