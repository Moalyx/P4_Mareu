package com.tuto.mareu.UI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tuto.mareu.R;
import com.tuto.mareu.di.DI;
import com.tuto.mareu.model.Meeting;
import com.tuto.mareu.service.MeetingApiService;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private MeetingApiService apiService;
    private List<Meeting> meetings;
    private RecyclerView recyclerView;
    private MyMeetingRecyclerViewAdapter adapter;
    private FloatingActionButton addMeetingButton;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = DI.getNeighbourApiService();

        initViews();

        configureToolbar();

        setAddMeetingButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initMeetingList();
    }

    private void initViews() {
        addMeetingButton = findViewById(R.id.faButton);
        recyclerView = findViewById(R.id.meeting_recyclerview);
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    private void initMeetingList() {
        meetings = apiService.getMeetings();
        adapter = new MyMeetingRecyclerViewAdapter(meetings, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showDatePickerDialog() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.show();
    }

    private void setAddMeetingButton() {
        addMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddReuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayFilteredMeetings(String room) {
        List<Meeting> filteredListByRoom = apiService.getMeetingsByRoom(room);
        adapter = new MyMeetingRecyclerViewAdapter(filteredListByRoom, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.filter_button:
                return true;

            case R.id.action_date:
                showDatePickerDialog();
                return true;

            case R.id.action_room:
                return true;

            case R.id.action_reset:
                initMeetingList();
                return true;

            default:
                displayFilteredMeetings(item.getTitle().toString());
                return true;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        String date = dayOfMonth + "/" + month + "/" + year;
        Toast.makeText(this,date,Toast.LENGTH_LONG).show();
        List<Meeting> filteredListByDate = apiService.getMeetingsByDate(date);
        adapter = new MyMeetingRecyclerViewAdapter(filteredListByDate, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}