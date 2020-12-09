package entities.event;

import java.io.Serializable;

public class Location implements Serializable {
    private final String name;
    private Integer capacity;
    private Integer tables;
    private Integer chairs;
    private Boolean hasInternet;
    private Boolean hasSoundSystem;
    private Boolean hasPresentationScreen;
    private String furtherNotes;

    public Location(String name, int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, String furtherNotes) {
        this.name = name;
        this.capacity = capacity;
        this.tables = tables;
        this.chairs = chairs;
        this.hasInternet = hasInternet;
        this.hasSoundSystem = hasSoundSystem;
        this.hasPresentationScreen = hasPresentationScreen;
        this.furtherNotes = furtherNotes;
    }

    public String getName(){
        return this.name;
    }

    public Integer getCapacity(){
        return this.capacity;
    }

    public Integer getTables(){
        return this.tables;
    }

    public Integer getChairs(){
        return this.chairs;
    }

    public Boolean getHasInternet(){
        return this.hasInternet;
    }

    public Boolean getHasSoundSystem(){
        return this.hasSoundSystem;
    }

    public Boolean getHasPresentationScreen(){
        return this.hasPresentationScreen;
    }

    public String getFurtherNotes() {
        return this.furtherNotes;
    }

    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }

    public void setTables(int newTables) {
        this.tables = newTables;
    }

    public void setChairs(int newChairs) {
        this.chairs = newChairs;
    }

    public void setHasInternet(boolean newHasInternet) {
        this.hasInternet = newHasInternet;
    }

    public void setHasSoundSystem(boolean newHasSoundSystem) {
        this.hasSoundSystem = newHasSoundSystem;
    }

    public void setHasPresentationScreen(boolean newHasPresentationScreen) {
        this.hasPresentationScreen = newHasPresentationScreen;
    }

    public void setFurtherNotes(String newFurtherNotes) {
        this.furtherNotes = newFurtherNotes;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Location) return ((Location) o).name.equals(this.name);
        return false;
    }

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
