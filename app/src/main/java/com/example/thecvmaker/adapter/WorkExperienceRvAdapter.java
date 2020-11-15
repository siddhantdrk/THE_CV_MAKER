package com.example.thecvmaker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thecvmaker.R;
import com.example.thecvmaker.models.WorkExpItem;

import java.util.List;

public class WorkExperienceRvAdapter extends RecyclerView.Adapter<WorkExperienceRvAdapter.WorkExperienceViewHolder> {

    Context context;
    List<WorkExpItem> WorkExpList;

    public WorkExperienceRvAdapter(Context context, List<WorkExpItem> EducationList) {
        this.context = context;
        this.WorkExpList = EducationList;
    }


    @NonNull
    @Override
    public WorkExperienceRvAdapter.WorkExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating new upcoming meeting card item
        View view = LayoutInflater.from(context).inflate(R.layout.work_experience_item, parent, false);
        return new WorkExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkExperienceRvAdapter.WorkExperienceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return WorkExpList.size();
    }

    public static class WorkExperienceViewHolder extends RecyclerView.ViewHolder {
        public WorkExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
