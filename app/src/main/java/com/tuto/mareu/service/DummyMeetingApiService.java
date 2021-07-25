package com.tuto.mareu.service;

import com.tuto.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();
    private final List<String> participants = DummyMeetingGenerator.generateParticipants();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsByDate(String date) {
        List<Meeting> filteredList = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            Meeting meeting = meetings.get(i);
            if (date.equals(meeting.getDate())) {
                filteredList.add(meeting);
            }
        }
        return filteredList;
    }

    @Override
    public List<Meeting> getMeetingsByRoom(String room) {
        List<Meeting> filteredList = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            Meeting meeting = meetings.get(i);
            if (room.equals(meeting.getRoom())) {
                filteredList.add(meeting);
            }
        }
        return filteredList;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public List<String> getParticipants() {
        return participants;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }
}
