package com.szelecki.immersion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.szelecki.immersion.R;

import com.szelecki.immersion.models.ModelLanguageStatistic;

import java.util.ArrayList;

public class NavigationViewLanguagesAdapter extends RecyclerView.Adapter<NavigationViewLanguagesAdapter.LanguagesViewHolder> {

    private ArrayList<ModelLanguageStatistic> models;
    private Context context;

    public NavigationViewLanguagesAdapter(ArrayList<ModelLanguageStatistic> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public LanguagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_navigation_view_item_language, parent, false);
        return new LanguagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguagesViewHolder holder, int position) {
        ModelLanguageStatistic model = models.get(position);
        holder.daysFrom.setText(model.getDaysFrom());
        holder.daysRecently.setText(model.getDaysRecently());
        holder.words.setText(model.getDictionaryWords());
        holder.imageView.setImageResource(model.getImage());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class LanguagesViewHolder extends RecyclerView.ViewHolder {
        TextView daysFrom, daysRecently, words;
        ImageView imageView;

        public LanguagesViewHolder(@NonNull View itemView) {
            super(itemView);
            daysFrom = itemView.findViewById(R.id.textViewLanguageStatisticDaysFrom);
            daysRecently = itemView.findViewById(R.id.textViewLanguageStatisticDaysRecently);
            words = itemView.findViewById(R.id.textViewLanguageStatisticWords);
            imageView = itemView.findViewById(R.id.imageViewLanguageStatistic);
        }
    }

    public void setLanguages(ArrayList<ModelLanguageStatistic> models) {
        this.models = models;
    }
}
