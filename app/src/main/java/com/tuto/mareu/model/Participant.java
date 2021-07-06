package com.tuto.mareu.model;

import java.util.Objects;

public class Participant {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String avatar;


    public Participant() {};
    public Participant(int id, String name, String surname, String email, String avatar) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.avatar = avatar;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String toString() {
        return email;

    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id &&
                name.equals(that.name) &&
                surname.equals(that.surname) &&
                email.equals(that.email) &&
                avatar.equals(that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, avatar);
    }
}
