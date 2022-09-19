package com.szelecki.immersion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.szelecki.immersion.R;

import java.util.ArrayList;

public class PostWordsAdapter extends RecyclerView.Adapter<PostWordsAdapter.PostWordsViewHolder> {

    private ArrayList<String> words;
    private Context context;

    public PostWordsAdapter(ArrayList<String> words, Context context) {
        this.words = words;
        this.context = context;
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
        holder.wordView.setText(words.get(position));
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class PostWordsViewHolder extends RecyclerView.ViewHolder {
        TextView wordView;

        public PostWordsViewHolder(@NonNull View itemView) {
            super(itemView);
            wordView = itemView.findViewById(R.id.wordViewHomePostItem);
        }
    }
}
