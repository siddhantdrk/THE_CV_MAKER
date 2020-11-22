package com.example.thecvmaker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thecvmaker.ProjectContributionActivity;
import com.example.thecvmaker.R;
import com.example.thecvmaker.models.ProjectContributionItem;

import java.util.List;

public class ProjectContributionRvAdapter extends RecyclerView.Adapter<ProjectContributionRvAdapter.ProjectContributionViewHolder> {
    Context context;
    List<ProjectContributionItem> ProjectContributionList;

    public ProjectContributionRvAdapter(Context context, List<ProjectContributionItem> projectContributionItemList) {
        this.context=context;
        this.ProjectContributionList=projectContributionItemList;
    }

    @NonNull
    @Override
    public ProjectContributionRvAdapter.ProjectContributionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating new upcoming meeting card item
        View view = LayoutInflater.from(context).inflate(R.layout.project_contribution_item, parent, false);
        return new ProjectContributionViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProjectContributionRvAdapter.ProjectContributionViewHolder holder, int position) {
        holder.projectTitle.setText(ProjectContributionList.get(position).getProjectTitle());
        holder.projectStartDate.setText(ProjectContributionList.get(position).getProjectStartDate());
        holder.projectEndDate.setText(ProjectContributionList.get(position).getProjectEndDate());
        holder.projectCategory.setText(ProjectContributionList.get(position).getProjectCategory());
        holder.projectDescription.setText(ProjectContributionList.get(position).getProjectDescription());
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectContributionList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ProjectContributionList.size();
    }

    public static class ProjectContributionViewHolder extends RecyclerView.ViewHolder {

        TextView projectTitle, projectStartDate, projectEndDate, projectCategory, projectDescription;
        ImageView removeBtn;

        public ProjectContributionViewHolder(@NonNull View itemView) {
            super(itemView);
            projectTitle = itemView.findViewById(R.id.project_title_text);
            projectStartDate = itemView.findViewById(R.id.project_start_date_txt);
            projectEndDate = itemView.findViewById(R.id.project_end_date_txt);
            projectCategory = itemView.findViewById(R.id.project_category_txt);
            projectDescription = itemView.findViewById(R.id.project_description_txt);
            removeBtn = itemView.findViewById(R.id.remove_btn_img);
        }
    }
}
