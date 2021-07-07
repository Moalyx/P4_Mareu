package com.tuto.mareu.service;

import com.tuto.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

    List<String> getParticipants();

    void createMeeting(Meeting meeting);
}
