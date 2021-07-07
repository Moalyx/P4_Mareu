package com.tuto.mareu.model;

import java.util.List;
import java.util.Objects;

public class Meeting {


    private String room;
    private String subject;
    private List<String> participants;
    private String time;
    private String date;
    private int image;


    public Meeting(String room, String subject, List<String> participants, String time, String date, int image) {
        this.room = room;
        this.subject = subject;
        this.participants = participants;
        this.time = time;
        this.date = date;
        this.image = image;
    }


    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String toString() {
        return participants.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return room.equals(meeting.room) &&
                subject.equals(meeting.subject) &&
                participants.equals(meeting.participants) &&
                time.equals(meeting.time) &&
                date.equals(meeting.date) &&
                image == meeting.image;
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, subject, participants, time, date, image);
    }
}
