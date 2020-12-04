package enums;

public enum StartCommand {
    LOGIN("1"),
    REGISTER("2");

    public final String command;

    StartCommand(String command) {
        this.command = command;
    }
}

