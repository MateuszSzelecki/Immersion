package com.szelecki.immersion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelPostFromFirebase;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private ArrayList<ModelPostFromFirebase> models;
    private Context context;

    public PostsAdapter(ArrayList<ModelPostFromFirebase> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == 1) {
            View view = inflater.inflate(R.layout.home_card_item_without, parent, false);
            return new PostsViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.home_card_item_with, parent, false);
            return new PostsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        ModelPostFromFirebase model = models.get(position);

        holder.name.setText(model.getUserName());
        holder.timeAgo.setText(model.getTimeAgo());
        holder.content.setText(model.getContentText());
        holder.likes.setText(String.valueOf(model.getLikesAmount()));
        holder.category1.setText(model.getCategory1());
        holder.category2.setText(model.getCategory2());
        holder.category3.setText(model.getCategory3());

        if (position%2 == 0) {
            holder.shadowCard.setCardBackgroundColor(context.getColor(R.color.main_blue));
        } else {
            holder.shadowCard.setCardBackgroundColor(context.getColor(R.color.main_yellow));
        }

        if (model.getWords().size() > 0) {
            PostWordsAdapter postWordsAdapter = new PostWordsAdapter(model.getWords(), context, position);
            holder.recyclerViewWords.setOverScrollMode(View.OVER_SCROLL_NEVER);
            holder.recyclerViewWords.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.recyclerViewWords.setAdapter(postWordsAdapter);
        }

        if (model.getContentImageURL().equals(".")) {
            holder.contentImage.setVisibility(View.GONE);
        }

        //TODO: zdjęcia
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (models.get(position).getWords().size() == 0) {
            return 1; //1 dla postu bez słówek ze słownika
        } else {
            return 2; //2 dla postu z słówkami ze słownika
        }
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView name, timeAgo, content, likes, comments, category1, category2, category3;
        ImageView profileImage, contentImage;
        MaterialCardView shadowCard;
        RecyclerView recyclerViewWords;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameHomePostItem);
            timeAgo = itemView.findViewById(R.id.timeAgoHomePostItem);
            content = itemView.findViewById(R.id.contentHomePostItem);
            likes = itemView.findViewById(R.id.likesHomePostItem);
            comments = itemView.findViewById(R.id.commentsHomePostItem);
            category1 = itemView.findViewById(R.id.category1HomePostItem);
            category2 = itemView.findViewById(R.id.category2HomePostItem);
            category3 = itemView.findViewById(R.id.category3HomePostItem);
            profileImage = itemView.findViewById(R.id.profileImageHomePostItem);
            contentImage = itemView.findViewById(R.id.contentImageHomePostItem);
            shadowCard = itemView.findViewById(R.id.shadowCardHomePostItem);
            recyclerViewWords = itemView.findViewById(R.id.recyclerViewWordsHomePostItem);
        }
    }
}
