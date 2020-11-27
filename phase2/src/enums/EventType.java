package enums;

public enum EventType {
    GENERAL_EVENT("1"),
    TALK("2"),
    NETWORKING_EVENT("3"),
    PANEL_DISCUSSION("4");

    public final String type;

    EventType(String type) { this.type = type; }
}
