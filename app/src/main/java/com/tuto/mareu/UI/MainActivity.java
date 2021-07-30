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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tuto.mareu.R;
import com.tuto.mareu.databinding.ActivityMainBinding;
import com.tuto.mareu.di.DI;
import com.tuto.mareu.model.Meeting;
import com.tuto.mareu.service.MeetingApiService;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ActivityMainBinding binding;
    private MeetingApiService apiService;
    private List<Meeting> meetings;
    private MyMeetingRecyclerViewAdapter adapter;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewBinding();

        apiService = DI.getNeighbourApiService();

        configureToolbar();

        setAddMeetingButton();
    }

    private void initViewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initMeetingList();
    }

    private void configureToolbar() {
        binding.toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(binding.toolbar);
    }

    private void initMeetingList() {
        meetings = apiService.getMeetings();
        adapter = new MyMeetingRecyclerViewAdapter(meetings, this);
        binding.meetingRecyclerview.setAdapter(adapter);
        binding.meetingRecyclerview.setLayoutManager(new LinearLayoutManager(this));
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
        binding.faButton.setOnClickListener(new View.OnClickListener() {
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
        binding.meetingRecyclerview.setAdapter(adapter);
        binding.meetingRecyclerview.setLayoutManager(new LinearLayoutManager(this));
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
        binding.meetingRecyclerview.setAdapter(adapter);
        binding.meetingRecyclerview.setLayoutManager(new LinearLayoutManager(this));

    }
}