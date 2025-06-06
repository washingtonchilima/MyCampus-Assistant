package com.example.mycampus_assistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private final List<ClassScheduleEntry> scheduleList;

    public ScheduleAdapter(List<ClassScheduleEntry> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayTextView, timeTextView, subjectTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.tvDay);
            timeTextView = itemView.findViewById(R.id.tvTime);
            subjectTextView = itemView.findViewById(R.id.tvSubject);
        }
    }

    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_schedule_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, int position) {
        ClassScheduleEntry entry = scheduleList.get(position);
        holder.dayTextView.setText(entry.getDay());
        holder.timeTextView.setText(entry.getTime());
        holder.subjectTextView.setText(entry.getSubject());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }
}
