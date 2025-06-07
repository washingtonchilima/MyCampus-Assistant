package com.example.mycampus_assistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssignmentsExamsAdapter extends RecyclerView.Adapter<AssignmentsExamsAdapter.ViewHolder> {

    private List<AssignmentExamItem> itemList;

    public AssignmentsExamsAdapter(List<AssignmentExamItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_assignment_exam, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AssignmentExamItem item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.dueDate.setText(item.getDueDate());
        holder.alertIcon.setVisibility(item.isAlert() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, dueDate;
        ImageView alertIcon;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            dueDate = itemView.findViewById(R.id.textDueDate);
            alertIcon = itemView.findViewById(R.id.iconAlert);
        }
    }
}
