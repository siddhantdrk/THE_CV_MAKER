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
import com.example.thecvmaker.models.EducationItem;

import java.util.List;

public class EducationRvAdapter extends RecyclerView.Adapter<EducationRvAdapter.EducationViewHolder> {

    Context context;
    List<EducationItem> EducationList;

    public EducationRvAdapter(Context context, List<EducationItem> EducationList) {
        this.context = context;
        this.EducationList = EducationList;
    }

    @NonNull
    @Override
    public EducationRvAdapter.EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating new upcoming meeting card item
        View view = LayoutInflater.from(context).inflate(R.layout.education_item, parent, false);
        return new EducationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationRvAdapter.EducationViewHolder holder, int position) {
        holder.degreeTitle.setText(EducationList.get(position).getEduDegreeTitle());
        holder.eduStartDate.setText(EducationList.get(position).getEduStartDate());
        holder.eduEndDate.setText(EducationList.get(position).getEduEndDate());
        holder.eduSchoolInstitute.setText(EducationList.get(position).getEduSchoolInstitute());
        holder.eduDescription.setText(EducationList.get(position).getEduDescription());

        holder.removeImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EducationList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return EducationList.size();
    }

    public static class EducationViewHolder extends RecyclerView.ViewHolder {
        TextView degreeTitle, eduStartDate, eduEndDate, eduSchoolInstitute, eduDescription;
        ImageView removeImgBtn;
        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            degreeTitle = itemView.findViewById(R.id.degree_title_text);
            eduStartDate = itemView.findViewById(R.id.edu_start_date_txt);
            eduEndDate = itemView.findViewById(R.id.edu_end_date_txt);
            eduSchoolInstitute = itemView.findViewById(R.id.edu_school_college_txt);
            eduDescription = itemView.findViewById(R.id.edu_description_txt);
            removeImgBtn = itemView.findViewById(R.id.remove_btn_img);
        }
    }
}
