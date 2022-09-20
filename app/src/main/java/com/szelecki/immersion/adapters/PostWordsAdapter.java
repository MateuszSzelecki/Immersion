package com.szelecki.immersion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelColor;
import com.szelecki.immersion.models.ModelWord;

import java.util.ArrayList;

public class PostWordsAdapter extends RecyclerView.Adapter<PostWordsAdapter.PostWordsViewHolder> {

    private ArrayList<ModelWord> words;
    private Context context;
    private ModelColor colorTheme;

    public PostWordsAdapter(ArrayList<ModelWord> words, Context context, ModelColor colorTheme) {
        this.words = words;
        this.context = context;
        this.colorTheme = colorTheme;
    }

    @NonNull
    @Override
    public PostWordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_card_item_word, parent, false);
        return new PostWordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostWordsViewHolder holder, int position) {
        ModelWord model = words.get(position);
        holder.textView.setText(model.getWord());
        holder.textView.setTextColor(colorTheme.getTextColor(position));
        holder.background.setCardBackgroundColor(colorTheme.getBackgroundColor(position));
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class PostWordsViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView background;

        public PostWordsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textWordHomePostItem);
            background = itemView.findViewById(R.id.backgroundWordHomePostItem);
        }
    }
}
