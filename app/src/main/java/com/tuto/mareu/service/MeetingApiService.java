package com.tuto.mareu.service;

import com.tuto.mareu.model.Meeting;
import com.tuto.mareu.model.Participant;
import com.tuto.mareu.model.Room;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    List<Participant> getParticipants();

    void addParticipant(Participant participant);

    void removeParticipant(Participant participant);

    List<Room> getRooms();

    void createMeeting(Meeting meeting);
}
