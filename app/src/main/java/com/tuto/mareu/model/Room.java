package com.tuto.mareu.model;

import java.util.Objects;

public class Room {

    private int id;
    private String name;
    private boolean available;


    public Room (){};
    public Room(int id, String name, boolean available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                available == room.available &&
                name.equals(room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, available);
    }
}
