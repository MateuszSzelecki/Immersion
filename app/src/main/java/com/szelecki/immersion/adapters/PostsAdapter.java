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
        View view = inflater.inflate(R.layout.home_card_item, parent, false);
        return new PostsViewHolder(view);
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

        if (model.getWords().size() == 0) {
            holder.recyclerViewWords.setVisibility(View.GONE);
        } else {
            PostWordsAdapter postWordsAdapter = new PostWordsAdapter(model.getWords(), context);
            holder.recyclerViewWords.setOverScrollMode(View.OVER_SCROLL_NEVER);
            holder.recyclerViewWords.setLayoutManager(new LinearLayoutManager(context));
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

    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView name, timeAgo, content, likes, comments, category1, category2, category3;
        ImageView profileImage, contentImage;
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
            recyclerViewWords = itemView.findViewById(R.id.recyclerViewWordsHomePostItem);
        }
    }
}
