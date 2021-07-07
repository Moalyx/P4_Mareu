package com.tuto.mareu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tuto.mareu.di.DI;
import com.tuto.mareu.model.Meeting;
import com.tuto.mareu.service.MeetingApiService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MeetingApiService apiService;
    List<Meeting> meetings;
    RecyclerView recyclerView;
    MyMeetingRecyclerViewAdapter myMeetingRecyclerViewAdapter;
    FloatingActionButton addMeetingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addMeetingButton = findViewById(R.id.faButton);
        apiService = DI.getNeighbourApiService();
        meetings = apiService.getMeetings();
        recyclerView = findViewById(R.id.meeting_recyclerview);
        myMeetingRecyclerViewAdapter = new MyMeetingRecyclerViewAdapter(meetings);
        configureToolbar();
        recyclerView.setAdapter(myMeetingRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setAddMeetingButton();
    }

    public void setAddMeetingButton() {
        addMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddReuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, R.string.filter, Toast.LENGTH_SHORT).show();
        return true;
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }
}