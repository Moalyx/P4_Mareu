package com.tuto.mareu.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tuto.mareu.R;
import com.tuto.mareu.model.Meeting;

import java.util.List;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> meetings;
    private final Context context;

    public MyMeetingRecyclerViewAdapter(List<Meeting> meetings, Context context) {
        this.meetings = meetings;
        this.context = context;
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
        String description = context.getString(R.string.description, meeting.getSubject(), meeting.getTime(), meeting.getRoom());
        holder.meeting.setText(description);
        holder.participants.setText(meeting.toString().replace("[", "").replace("]", ""));
        holder.circle.setImageResource(meeting.getImage());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetings.remove(meeting);
                notifyItemRemoved(position);
                //notifyItemRangeChanged(position, meetings.size());
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
