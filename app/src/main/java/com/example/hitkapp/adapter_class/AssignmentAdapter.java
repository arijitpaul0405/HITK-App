package com.example.hitkapp.adapter_class;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hitkapp.R;
import com.example.hitkapp.custom_class.AssignmentClass;
import com.example.hitkapp.databinding.AssignmentListDesignBinding;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.UIHolder> {
    Context context;
    List<AssignmentClass> list;

    public void setFilteredList(List<AssignmentClass> filteredList) {
        this.list = filteredList;
        notifyDataSetChanged();
    }

    public AssignmentAdapter(Context context, List<AssignmentClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UIHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View uiView = inflater.inflate(R.layout.assignment_list_design, parent, false);
        return new UIHolder(uiView);
    }

    @Override
    public void onBindViewHolder(@NonNull UIHolder holder, int position) {
        holder.binding.name.setText(list.get(position).getName());
        holder.binding.downloadBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getLink()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UIHolder extends RecyclerView.ViewHolder {
        AssignmentListDesignBinding binding;
        public UIHolder(@NonNull View itemView) {
            super(itemView);
            binding = AssignmentListDesignBinding.bind(itemView);
        }
    }
}
