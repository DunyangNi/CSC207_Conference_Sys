package Enums;

public enum OrganizerCommand {
    EXIT("00"),
    LOGOUT("0"),
    NEWSPK("1"),
    VIEWALLACC("2"),
    ADDCONTACT("3"),
    REMCONTACT("4"),
    VIEWCONTACTS("5"),
    MSGSPEAK("6"),
    MSGATT("7"),
    MSGALLSPEAK("8"),
    MSGALLATT("9"),
    VIEWCONVO("10"),
    ADDROOM("11"),
    VIEWROOMS("12"),
    ADDEVENT("13"),
    CANCELEVENT("14"),
    RESCHED("15"),
    VIEWTALKSCHED("16"),
    VIEWMENU("*");

    public final String command;

    private OrganizerCommand(String command) {
        this.command = command;
    }
}
