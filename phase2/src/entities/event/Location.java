package entities.event;

import java.io.Serializable;

/**
 * Represents a location in the conference.
 */
public class Location implements Serializable {
    private final String name;
    private Integer capacity;
    private Integer tables;
    private Integer chairs;
    private Boolean hasInternet;
    private Boolean hasSoundSystem;
    private Boolean hasPresentationScreen;
    private String furtherNotes;

    /**
     * Creates an instance of <code>Location</code> with inventory and feature information.
     *
     * @param name                  given name
     * @param capacity              given capacity
     * @param tables                given number of tables
     * @param chairs                given number of chairs
     * @param hasInternet           given existence of internet
     * @param hasSoundSystem        given existence of sound system
     * @param hasPresentationScreen given existence of presentation screen
     * @param furtherNotes          given further notes
     */
    public Location(String name, int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem,
                    boolean hasPresentationScreen, String furtherNotes) {
        this.name = name;
        this.capacity = capacity;
        this.tables = tables;
        this.chairs = chairs;
        this.hasInternet = hasInternet;
        this.hasSoundSystem = hasSoundSystem;
        this.hasPresentationScreen = hasPresentationScreen;
        this.furtherNotes = furtherNotes;
    }

    /**
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return capacity
     */
    public Integer getCapacity() {
        return this.capacity;
    }

    /**
     * @return number of tables
     */
    public Integer getTables() {
        return this.tables;
    }

    /**
     * @return number of chairs
     */
    public Integer getChairs() {
        return this.chairs;
    }

    /**
     * @return existence of internet
     */
    public Boolean getHasInternet() {
        return this.hasInternet;
    }

    /**
     * @return existence of sound system
     */
    public Boolean getHasSoundSystem() {
        return this.hasSoundSystem;
    }

    /**
     * @return existence of presentation screen
     */
    public Boolean getHasPresentationScreen() {
        return this.hasPresentationScreen;
    }

    /**
     * @return further notes
     */
    public String getFurtherNotes() {
        return this.furtherNotes;
    }

    /**
     * sets capacity
     *
     * @param newCapacity the intended new capacity
     */
    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }

    /**
     * sets tables
     *
     * @param newTables the intended new tables
     */
    public void setTables(int newTables) {
        this.tables = newTables;
    }

    /**
     * sets chairs
     *
     * @param newChairs the intended new chairs
     */
    public void setChairs(int newChairs) {
        this.chairs = newChairs;
    }

    /**
     * sets HasInternet
     *
     * @param newHasInternet the intended new HasInternet
     */
    public void setHasInternet(boolean newHasInternet) {
        this.hasInternet = newHasInternet;
    }

    /**
     * sets HasSoundSystem
     *
     * @param newHasSoundSystem the intended new HasSoundSystem
     */
    public void setHasSoundSystem(boolean newHasSoundSystem) {
        this.hasSoundSystem = newHasSoundSystem;
    }

    /**
     * sets HasPresentationScreen
     *
     * @param newHasPresentationScreen the intended new HasPresentationScreen
     */
    public void setHasPresentationScreen(boolean newHasPresentationScreen) {
        this.hasPresentationScreen = newHasPresentationScreen;
    }

    /**
     * sets FurtherNotes
     *
     * @param newFurtherNotes the intended new FurtherNotes
     */
    public void setFurtherNotes(String newFurtherNotes) {
        this.furtherNotes = newFurtherNotes;
    }

    /**
     * Compares for equality with another object.
     *
     * @param o an object to compare with
     * @return True if o is an instance of Location and has the same name.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Location) return ((Location) o).name.equals(this.name);
        return false;
    }

    /**
     * @return A string representation of this <code>Location</code>
     */
    @Override
    public String toString() {
        return "Location Name: " + this.name + "\n" +
                "Capacity: " + this.capacity.toString() + "\n" +
                "Number of tables: " + this.tables.toString() + "\n" +
                "Number of chairs: " + this.chairs.toString() + "\n" +
                "Internet Access: " + this.hasInternet.toString() + "\n" +
                "Sound System: " + this.hasSoundSystem.toString() + "\n" +
                "Presentation Screen: " + this.hasPresentationScreen + "\n" +
                "Further notes:\n" + this.furtherNotes;
    }
}
