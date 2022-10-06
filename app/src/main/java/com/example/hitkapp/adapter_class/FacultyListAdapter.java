package com.example.hitkapp.adapter_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hitkapp.R;
import com.example.hitkapp.custom_class.FacultyClass;
import com.example.hitkapp.databinding.FacultyListDesignBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FacultyListAdapter extends RecyclerView.Adapter<FacultyListAdapter.UIHolder> {
    Context context;
    List<FacultyClass> list;

    public void setFilteredList(List<FacultyClass> filteredList) {
        this.list = filteredList;
        notifyDataSetChanged();
    }

    public FacultyListAdapter(Context context, List<FacultyClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UIHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View uiView = inflater.inflate(R.layout.faculty_list_design, parent, false);
        return new UIHolder(uiView);
    }

    @Override
    public void onBindViewHolder(@NonNull UIHolder holder, int position) {
        FacultyClass faculty = list.get(position);
        Picasso.get()
                .load(faculty.getImage())
                .into(holder.binding.image);
        holder.binding.name.setText(faculty.getName());
        holder.binding.designation.setText(faculty.getDesignation());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UIHolder extends RecyclerView.ViewHolder {
        FacultyListDesignBinding binding;
        public UIHolder(@NonNull View itemView) {
            super(itemView);
            binding = FacultyListDesignBinding.bind(itemView);
        }
    }
}
