package com.tuto.mareu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tuto.mareu.model.Meeting;

import java.util.List;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {


    private List<Meeting> meetings;

    public MyMeetingRecyclerViewAdapter(List<Meeting> meetings) {
        this.meetings = meetings;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyMeetingRecyclerViewAdapter.ViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);
        holder.meeting.setText(meeting.getRoom() + " - " + meeting.getSubject() + " - " + meeting.getDate() + " - " + meeting.getTime());
        holder.participants.setText(meeting.toString().replace("[", "").replace("]", ""));
        holder.circle.setImageResource(meeting.getImage());


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetings.remove(meeting);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, meetings.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return meetings.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView meeting;
        public TextView participants;
        public ImageButton deleteButton;
        public ImageView circle;


        public ViewHolder(View itemView) {
            super(itemView);

            meeting = itemView.findViewById(R.id.meeting);
            deleteButton = itemView.findViewById(R.id.delete_button);
            participants = itemView.findViewById(R.id.participants);
            circle = itemView.findViewById(R.id.circle);

        }
    }
}
