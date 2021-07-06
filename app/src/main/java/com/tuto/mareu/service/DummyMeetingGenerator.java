package com.tuto.mareu.service;

import com.tuto.mareu.R;
import com.tuto.mareu.model.Meeting;
import com.tuto.mareu.model.Participant;
import com.tuto.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

    private List<Participant> participants = DummyMeetingGenerator.generateParticipants();
    public static List<Participant> DUMMY_PARTICIPANTS = Arrays.asList(
            new Participant(1, "Shin", "Hyou", "shin@kingdom.com", "https://i.pravatar.cc/150?u=a042581f4e29026703b"),
            new Participant(2, "Gatsu", "Casca", "Gatsu@berserk.com", "https://i.pravatar.cc/150?u=a042581f4e29026703b"),
            new Participant(3, "Luffy", "Zorro", "Luffy@onepiece.com", "https://i.pravatar.cc/150?u=a042581f4e29026703b"),
            new Participant(4, "Emma", "Phil", "Emma@thepromisedneverland.com", "https://i.pravatar.cc/150?u=a042581f4e29026703b"),
            new Participant(5, "Jinwoo", "Igris", "Jinwoo@sololeveling.com", "https://i.pravatar.cc/150?u=a042581f4e29026703b"),
            new Participant(6, "Saïtama", "Genos", "Saïtama@onepunchman.com", "https://i.pravatar.cc/150?u=a042581f4e29026703b")
    );

    static List<Participant> generateParticipants() {
        return new ArrayList<>(DUMMY_PARTICIPANTS);
    }

    private List<Room> rooms = DummyMeetingGenerator.generateRooms();
    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room(1, "Salle Maréu 1", true),
            new Room(2, "Salle Maréu 2", true),
            new Room(3, "Salle Maréu 3", true),
            new Room(4, "Salle Maréu 4", true),
            new Room(5, "Salle Maréu 5", true),
            new Room(6, "Salle Maréu 6", true),
            new Room(7, "Salle Maréu 7", true),
            new Room(8, "Salle Maréu 8", true),
            new Room(9, "Salle Maréu 9", true),
            new Room(10, "Salle Maréu 10", true)
    );

    static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();
    public static List<Meeting> Dummy_MEETINGS = Arrays.asList(
            new Meeting("Salle 1", "Combat", generateParticipants(), "15h50", "01/08/21", R.drawable.ic_circle),
            new Meeting("Salle 2", "Training", generateParticipants(), "17h20", "11/08/21", R.drawable.ic_circle_blue),
            new Meeting("Salle 3", "Stratégie", generateParticipants(), "12h00", "03/09/21", R.drawable.ic_circle_green),
            new Meeting("Salle 4", "Méditation", generateParticipants(), "08h10", "24/10/21", R.drawable.ic_circle_pink),
            new Meeting("Salle 5", "Dégustation", generateParticipants(), "20h30", "02/11/21", R.drawable.ic_circle_yellow)
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(Dummy_MEETINGS);
    }


}
