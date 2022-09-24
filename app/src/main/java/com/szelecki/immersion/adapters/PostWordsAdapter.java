package com.szelecki.immersion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelWord;

import java.util.ArrayList;

public class PostWordsAdapter extends RecyclerView.Adapter<PostWordsAdapter.PostWordsViewHolder> {

    private ArrayList<ModelWord> words;
    private Context context;
    private int postPosition;

    public PostWordsAdapter(ArrayList<ModelWord> words, Context context, int postPosition) {
        this.words = words;
        this.context = context;
        this.postPosition = postPosition;
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
        holder.textWord.setText(model.getWord());
        holder.textMeaning.setText(model.getMeaning());

        if (postPosition%2 == 0) {
            holder.textWord.setTextColor(context.getColor(R.color.main_blue));
            holder.backgroundMeaning.setCardBackgroundColor(context.getColor(R.color.main_blue));
        } else {
            holder.textWord.setTextColor(context.getColor(R.color.main_yellow));
            holder.backgroundMeaning.setCardBackgroundColor(context.getColor(R.color.main_yellow));
        }

        holder.backgroundWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.backgroundMeaning.getVisibility() == View.GONE) {
                    holder.backgroundMeaning.setVisibility(View.VISIBLE);
                } else {
                    holder.backgroundMeaning.setVisibility(View.GONE);
                }
            }
        });

        holder.backgroundMeaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.backgroundMeaning.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class PostWordsViewHolder extends RecyclerView.ViewHolder {
        TextView textWord, textMeaning;
        MaterialCardView backgroundWord, backgroundMeaning;

        public PostWordsViewHolder(@NonNull View itemView) {
            super(itemView);
            textWord = itemView.findViewById(R.id.textWordHomePostItem);
            textMeaning = itemView.findViewById(R.id.textMeaningHomePostItem);
            backgroundWord = itemView.findViewById(R.id.backgroundWordHomePostItem);
            backgroundMeaning = itemView.findViewById(R.id.backgroundMeaningHomePostItem);
        }
    }
}
