package com.tuto.mareu.service;

import com.tuto.mareu.model.Meeting;
import com.tuto.mareu.model.Participant;
import com.tuto.mareu.model.Room;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();
    private List<Participant> participants =DummyMeetingGenerator.generateParticipants();
    private List<Room> rooms = DummyMeetingGenerator.generateRooms();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);

    }



    @Override
    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    @Override
    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }
}
