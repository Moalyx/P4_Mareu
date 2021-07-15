package com.tuto.mareu;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.tuto.mareu.di.DI;
import com.tuto.mareu.model.Meeting;
import com.tuto.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class AddReuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton;
    private Button timeButton;
    private Spinner roomSpinner;
    private TextView participantSpinner;
    private Spinner colorSpinner;
    private Button saveButton;
    private List<String> participantList = new ArrayList<>();
    private List<Integer> participantsReu = new ArrayList<>();
    private MeetingApiService meetingApiService;
    boolean[] selectedParticipants;
    private String[] participantsArray;
    private TextInputLayout textInputSubject;
    private ColorSpinnerAdapter adapter;
    int colorMeeting;
    int colorList[] = {R.drawable.ic_circle_blue, R.drawable.ic_circle_green, R.drawable.ic_circle_pink, R.drawable.ic_circle_yellow, R.drawable.ic_circle};
    DatePickerDialog.OnDateSetListener setDateListener;
    TimePickerDialog.OnTimeSetListener setTimeListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reu);
        meetingApiService = DI.getNeighbourApiService();

        dateButton = findViewById(R.id.datePickerButton);
        timeButton = findViewById(R.id.timePickerButton);
        roomSpinner = findViewById(R.id.roomSpinner);
        colorSpinner = findViewById(R.id.colorSpinner);
        saveButton = findViewById(R.id.saveButton);
        participantSpinner = findViewById(R.id.participantSpinner);
        participantsArray = getResources().getStringArray(R.array.Participants);
        textInputSubject = findViewById(R.id.sbuject_text_input);

        configureDatePicker();
        configureTimePicker();
        configureRoomSpinner();
        configureParticipantSpinner();
        configureColorSpinner();
        initList();
        addMeeting();
    }

    private void configureParticipantSpinner() {
        selectedParticipants = new boolean[participantsArray.length];
        participantSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddReuActivity.this);
                builder.setTitle(R.string.list_of_participants);
                builder.setCancelable(false);

                builder.setMultiChoiceItems(participantsArray, selectedParticipants, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean selected) {

                        if (selected) {
                            participantsReu.add(position);
                            Collections.sort(participantsReu);
                        } else {
                            participantsReu.remove(position);
                        }

                    }
                });

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j = 0; j < participantsReu.size(); j++) {
                            stringBuilder.append(participantsArray[participantsReu.get(j)]);

                            participantList.add(participantsArray[participantsReu.get(j)]);


//                            if (j != participantsReu.size() - 1) {
                            stringBuilder.append(", ");
//                            }
                        }
                        participantSpinner.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();

                    }
                });
                builder.show();
            }
        });

    }



    private void configureDatePicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(AddReuActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        dateButton.setText(date);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void configureTimePicker() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(AddReuActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        String time = hour + "H" + minute;
                        timeButton.setText(time);
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });
    }

    public int selectedColor (int position){
        colorMeeting = (int) colorSpinner.getSelectedItemPosition();
        switch (colorSpinner.getSelectedItemPosition()) {

            case 0:
                colorMeeting = (R.drawable.ic_circle_blue);
                break;

            case 1:
                colorMeeting = (R.drawable.ic_circle_green);
                break;

            case 2:
                colorMeeting = (R.drawable.ic_circle_pink);
                break;

            case 3:
                colorMeeting = (R.drawable.ic_circle_yellow);
                break;

            case 4:
                colorMeeting = (R.drawable.ic_circle);
                break;

            default:
                break;
        }
        return colorMeeting;
    }

    private void addMeeting() {

        //int positionColor = colorMeeting;
        saveButton.setEnabled(true);
        selectedColor(colorMeeting);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String room = roomSpinner.getSelectedItem().toString();
                String subject = textInputSubject.getEditText().getText().toString();
                List<String> participants = participantList;
                String time = timeButton.getText().toString();
                String date = dateButton.getText().toString();
                int color = selectedColor(colorSpinner.getSelectedItemPosition());

                Meeting meeting = new Meeting(room, subject, participants, time, date, color);
//                Meeting meeting = new Meeting(
//                        roomSpinner.getSelectedItem().toString(),
//                        textInputSubject.getEditText().getText().toString(),
//                        participantList,
//                        timeButton.getText().toString(),
//                        dateButton.getText().toString(),
////                        positionColor
//                        colorSpinner.getSelectedItemPosition()
//                );
                meetingApiService.createMeeting(meeting);
                Intent intent = new Intent(AddReuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initList() {

        textInputSubject.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                //saveButton.setEnabled(s.length() > 0);
            }
        });
    }

    private void configureRoomSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Salle_Maréu, R.layout.cutom_spinner_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        roomSpinner.setAdapter(adapter);
        roomSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedRoom = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), selectedRoom, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void configureColorSpinner() {
        adapter = new ColorSpinnerAdapter(AddReuActivity.this, colorList);
        colorSpinner.setAdapter(adapter);
    }


}