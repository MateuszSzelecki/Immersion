package com.szelecki.immersion.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.szelecki.immersion.R;
import com.szelecki.immersion.activities.AddLanguageActivity;
import com.szelecki.immersion.models.ModelLanguageStatistic;

import java.util.ArrayList;

public class AddLanguageAdapter extends RecyclerView.Adapter<AddLanguageAdapter.AddLanguageViewHolder> {

    private ArrayList<ModelLanguageStatistic> models;
    private ArrayList<String> representations;
    private Context context;
    private Drawable selectedDrawable;

    OnClickLanguageItemInterface listener;

    public void setOnClickLanguageItemInterface(OnClickLanguageItemInterface mListener) {
        this.listener = mListener;
    }

    public AddLanguageAdapter(ArrayList<ModelLanguageStatistic> models, ArrayList<String> representations, Context context, Drawable selectedDrawable) {
        this.models = models;
        this.representations = representations;
        this.context = context;
        this.selectedDrawable = selectedDrawable;
    }

    @NonNull
    @Override
    public AddLanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.addlanguage_recyclerview_item, parent, false);
        return new AddLanguageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddLanguageViewHolder holder, int position) {
        ModelLanguageStatistic model = models.get(position);
        holder.imageView.setImageResource(model.getImage());
        holder.description.setText(model.getName());
        holder.representation.setText(representations.get(position));
        if (model.getWords() == 1) {
            holder.background.setBackground(selectedDrawable);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    class AddLanguageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView description, representation;
        ConstraintLayout background;

        public AddLanguageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewAddLanguageItem);
            description = itemView.findViewById(R.id.textView1AddLanguageItem);
            representation = itemView.findViewById(R.id.textView2AddLanguageItem);
            background = itemView.findViewById(R.id.backgroundAddLanguageItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (models.get(getAdapterPosition()).getWords() == 0) {
                        listener.addLanguage(getAdapterPosition());
                    }
                }
            });
        }
    }
}
