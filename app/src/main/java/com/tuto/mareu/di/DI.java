package com.tuto.mareu.di;

import com.tuto.mareu.service.DummyMeetingApiService;
import com.tuto.mareu.service.MeetingApiService;

public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getNeighbourApiService() {
        return service;
    }

    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }

}
