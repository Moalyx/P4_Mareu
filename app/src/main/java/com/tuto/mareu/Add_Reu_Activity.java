package com.tuto.mareu;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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

public class Add_Reu_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton;
    private Button timeButton;
    private Spinner roomSpinner;
    private TextView participantSpinner;
    private Spinner colorSpinner;
    private Button saveButton;
    private ArrayList<Integer> participantsReu = new ArrayList<>();
    private MeetingApiService meetingApiService;
    boolean[] selectedParticipants;
    private String[] participants;
    private TextInputLayout textInputSubject;
    private ColorSpinnerAdapter adapter;
    //int colorList[] = {R.array.color};
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
        participants = getResources().getStringArray(R.array.Participants);
        textInputSubject = findViewById(R.id.sbuject_text_input);

        configureDatePicker();
        configureTimePicker();
        configureRoomSpinner();
        configureParticipantSpinner();
        configureColorSpinner();
        addMeeting();

    }

    private void configureParticipantSpinner() {
        selectedParticipants = new boolean[participants.length];
        participantSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Add_Reu_Activity.this);
                builder.setTitle("liste des participants");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(participants, selectedParticipants, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {

                        if (b) {
                            participantsReu.add(i);
                            Collections.sort(participantsReu);
                        } else {
                            participantsReu.remove(i);
                        }

                    }
                });

                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j = 0; j < participantsReu.size(); j++) {
                            stringBuilder.append(participants[participantsReu.get(j)]);

                            if (j != participantsReu.size() - 1) {
                                stringBuilder.append("@gmail.com"+", ");
                            }
                        }
                        participantSpinner.setText(stringBuilder.toString()+"@gmail.com");
                    }
                });

                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();

                    }
                });
                builder.setNeutralButton("Tout effacer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        for (int j = 0; j < selectedParticipants.length; j++) {
                            selectedParticipants[j] = false;
                            participantsReu.clear();
                            participantSpinner.setText("");
                        }

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
                datePickerDialog = new DatePickerDialog(Add_Reu_Activity.this, new DatePickerDialog.OnDateSetListener() {
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
                timePickerDialog = new TimePickerDialog(Add_Reu_Activity.this, new TimePickerDialog.OnTimeSetListener() {
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

    private void addMeeting() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meeting meeting = new Meeting(
                        roomSpinner.toString(),
                        textInputSubject.getEditText().toString(),
                        dateButton.getText().toString(),
                        timeButton.getText().toString(),
                        participantSpinner.getText().toString(),
                        colorSpinner
                );
                meetingApiService.createMeeting(meeting);
                finish();
            }
        });
    }

    private void configureRoomSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Salle_Maréu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomSpinner.setAdapter(adapter);
        roomSpinner.setOnItemSelectedListener(this);
        roomSpinner.toString();

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
        adapter = new ColorSpinnerAdapter(Add_Reu_Activity.this, colorList);
        colorSpinner.setAdapter(adapter);

//        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,R.array.color, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        colorSpinner.setAdapter(adapter);
//        colorSpinner.setOnItemSelectedListener(this);
    }


}