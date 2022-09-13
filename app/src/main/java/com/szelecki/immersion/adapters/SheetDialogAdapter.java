package com.szelecki.immersion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelLanguageStatistic;

import java.util.ArrayList;

public class SheetDialogAdapter extends RecyclerView.Adapter<SheetDialogAdapter.LanguagesImageViewHolder> {

    private ArrayList<ModelLanguageStatistic> models;
    private Context context;

    OnClickLanguageItemInterface listener;

    public void setOnClickLanguageItemInterface(OnClickLanguageItemInterface mListener) {
        this.listener = mListener;
    }

    public SheetDialogAdapter(ArrayList<ModelLanguageStatistic> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public LanguagesImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_sheet_select_language_item, parent, false);
        return new LanguagesImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguagesImageViewHolder holder, int position) {
        ModelLanguageStatistic model = models.get(position);
        holder.imageView.setImageResource(model.getImage());
        if (position == models.size()-1 && models.size() < 4) {
            holder.imageViewAdd.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class LanguagesImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ConstraintLayout imageViewAdd;

        public LanguagesImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewSheet);
            imageViewAdd = itemView.findViewById(R.id.imageViewSheetAdd);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.changeUserLanguage(getAdapterPosition());
                }
            });

            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.deleteLanguage(getAdapterPosition());
                    return true;
                }
            });

            imageViewAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.addLanguage(getAdapterPosition());
                }
            });
        }
    }
}
