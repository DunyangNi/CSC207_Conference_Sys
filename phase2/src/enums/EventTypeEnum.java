package enums;

public enum EventTypeEnum {
    GENERAL_EVENT("0"),
    TALK("2"),
    NETWORKING_EVENT("1"),
    PANEL_DISCUSSION("3"),
    INVALID(null);

    public final String type;

    EventTypeEnum(String type) { this.type = type; }

    public static EventTypeEnum fromString(String stringVal) {
        for (EventTypeEnum e : EventTypeEnum.values()) {
            if (e.type != null && e.type.equals(stringVal))
                return e;
        }
        return INVALID;
    }
}

