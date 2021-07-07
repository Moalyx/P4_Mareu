package com.tuto.mareu.service;

import com.tuto.mareu.R;
import com.tuto.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

    private List<String> participants = DummyMeetingGenerator.generateParticipants();
    public static List<String> DUMMY_PARTICIPANTS = Arrays.asList("Shin@gmail.com", "Gatsu@gmail.com", "Luffy@gmail.com", "Emma@gmail.com", "Jinwoo@gmail.com", "Saïtama@gmail.com"
    );

    static List<String> generateParticipants() {
        return new ArrayList<>(DUMMY_PARTICIPANTS);
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
