package com.example.educativebuddies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<course> originalCourses;
    private List<course> filteredCourses;
    private CourseFilter courseFilter;

    public CourseAdapter(Context context, ArrayList<course> courses) {
        this.context = context;
        this.originalCourses = new ArrayList<>(courses);
        this.filteredCourses = new ArrayList<>(courses);
        this.courseFilter = new CourseFilter();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_course, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        course course = filteredCourses.get(position);
        holder.name.setText(course.getName());
        holder.description.setText(course.getDescription());
        holder.credits.setText("Credit Hours: " + course.getCredits());
    }

    @Override
    public int getItemCount() {
        return filteredCourses.size();
    }

    @Override
    public Filter getFilter() {
        return courseFilter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;
        TextView credits;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.course1_name);
            description = itemView.findViewById(R.id.course1_description);
            credits = itemView.findViewById(R.id.course1_credits);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(context,AssignmentActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    private class CourseFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = originalCourses;
                results.count = originalCourses.size();
            } else {
                List<course> filteredList = new ArrayList<>();

                for (course course : originalCourses) {
                    if (course.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(course);
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredCourses = (List<course>) results.values;
            notifyDataSetChanged();
        }
    }
}
