package com.example.thecvmaker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        holder.companyTitle.setText(WorkExpList.get(position).getCompany());
        holder.expStartDate.setText(WorkExpList.get(position).getStart_date());
        holder.expEndDate.setText(WorkExpList.get(position).getEnd_date());
        holder.expPosition.setText(WorkExpList.get(position).getPosition());
        holder.expDescription.setText(WorkExpList.get(position).getDescription());
        holder.removeImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkExpList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return WorkExpList.size();
    }

    public static class WorkExperienceViewHolder extends RecyclerView.ViewHolder {
        TextView companyTitle, expStartDate, expEndDate, expPosition, expDescription;
        ImageView removeImgBtn;

        public WorkExperienceViewHolder(@NonNull View itemView) {


            super(itemView);
            companyTitle = itemView.findViewById(R.id.exp_company_txt);
            expStartDate = itemView.findViewById(R.id.exp_start_date_txt);
            expEndDate = itemView.findViewById(R.id.exp_end_date_txt);
            expPosition = itemView.findViewById(R.id.position_text);
            expDescription = itemView.findViewById(R.id.exp_description_txt);
            removeImgBtn = itemView.findViewById(R.id.remove_btn_img);
        }
    }
}
