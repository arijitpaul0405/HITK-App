package com.example.hitkapp.adapter_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hitkapp.R;
import com.example.hitkapp.custom_class.GalleryClass;
import com.example.hitkapp.databinding.GalleryListDesignBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.UIHolder> {
    Context context;
    List<GalleryClass> list;

    public GalleryAdapter(Context context, List<GalleryClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UIHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View uiView = inflater.inflate(R.layout.gallery_list_design, parent, false);
        return new UIHolder(uiView);
    }

    @Override
    public void onBindViewHolder(@NonNull UIHolder holder, int position) {
        GalleryClass gallery = list.get(position);

        Picasso.get()
                .load(gallery.getImage())
                .into(holder.binding.imageview);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UIHolder extends RecyclerView.ViewHolder {
        GalleryListDesignBinding binding;
        public UIHolder(@NonNull View itemView) {
            super(itemView);
            binding = GalleryListDesignBinding.bind(itemView);
        }
    }
}
