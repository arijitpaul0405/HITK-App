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
import com.example.hitkapp.custom_class.SyllabusClass;
import com.example.hitkapp.databinding.SyllabusListDesignBinding;

import java.util.List;

public class SyllabusListAdapter extends RecyclerView.Adapter<SyllabusListAdapter.UIHolder> {
    Context context;
    List<SyllabusClass> list;

    public SyllabusListAdapter(Context context, List<SyllabusClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UIHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View uiView = inflater.inflate(R.layout.syllabus_list_design, parent, false);
        return new UIHolder(uiView);
    }

    @Override
    public void onBindViewHolder(@NonNull UIHolder holder, int position) {
        SyllabusClass syllabusClass = list.get(position);
        holder.binding.syllabusBtn.setText(syllabusClass.getTitle());
        holder.binding.syllabusBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(syllabusClass.getLink()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UIHolder extends RecyclerView.ViewHolder {
        SyllabusListDesignBinding binding;
        public UIHolder(@NonNull View itemView) {
            super(itemView);
            binding = SyllabusListDesignBinding.bind(itemView);
        }
    }
}
