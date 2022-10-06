package com.example.hitkapp.adapter_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hitkapp.R;
import com.example.hitkapp.custom_class.NoticeClass;
import com.example.hitkapp.databinding.NoticeDisplayDesignBinding;

import java.util.List;

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.UIHolder> {
    Context context;
    List<NoticeClass> list;

    public NoticeListAdapter(Context context, List<NoticeClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeListAdapter.UIHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View uiView = inflater.inflate(R.layout.notice_display_design, parent, false);
        return new UIHolder(uiView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeListAdapter.UIHolder holder, int position) {
        NoticeClass notice = list.get(position);

        holder.binding.noticeTextView.setText(notice.getNotice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UIHolder extends RecyclerView.ViewHolder {
        NoticeDisplayDesignBinding binding;
        public UIHolder(@NonNull View itemView) {
            super(itemView);
            binding = NoticeDisplayDesignBinding.bind(itemView);
        }
    }
}
