package com.tuto.mareu;

import com.tuto.mareu.di.DI;
import com.tuto.mareu.model.Meeting;
import com.tuto.mareu.service.DummyMeetingGenerator;
import com.tuto.mareu.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static com.tuto.mareu.service.DummyMeetingGenerator.DUMMY_PARTICIPANTS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private MeetingApiService service;
    private MyMeetingRecyclerViewAdapter adapter;


    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.Dummy_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting meeting = new Meeting("salle 1", "Stratégie", DUMMY_PARTICIPANTS, "18h30", "01/08/21", 2);
        service.createMeeting(meeting);
        assertTrue(service.getMeetings().contains(meeting));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meeting = new Meeting("salle 1", "Stratégie", DUMMY_PARTICIPANTS, "18h30", "01/08/21", 2);
        service.createMeeting(meeting);
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

     @Test
    public void getMeetingFilteredByDateWithSuccess(){
        List<Meeting> meetings = service.getMeetingsByDate("11/7/2021");
        assertEquals(meetings.size(), 2);
    }

    @Test
    public void getMeetingFilteredByRoomWithSuccess(){
        List<Meeting> meetings = service.getMeetingsByRoom("Salle 1");
        assertFalse(meetings.isEmpty());
    }

}