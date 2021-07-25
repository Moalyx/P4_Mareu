package com.tuto.mareu.service;

import com.tuto.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    List<Meeting> getMeetingsByDate(String date);

    List<Meeting> getMeetingsByRoom(String room);

    void deleteMeeting(Meeting meeting);

    List<String> getParticipants();

    void createMeeting(Meeting meeting);
}
