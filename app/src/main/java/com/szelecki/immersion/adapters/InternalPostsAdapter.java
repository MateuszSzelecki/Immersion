package com.szelecki.immersion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelPostFirebase;

import java.util.ArrayList;

public class InternalPostsAdapter extends RecyclerView.Adapter<InternalPostsAdapter.PostsViewHolder> {

    private ArrayList<ModelPostFirebase> models;
    private Context context;

    public InternalPostsAdapter(ArrayList<ModelPostFirebase> models, Context context) {
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
        ModelPostFirebase model = models.get(position);
        holder.name.setText(model.getName());
        holder.timeAgo.setText(model.getTimeAgo());
        holder.content.setText(model.getContentText());
        holder.likes.setText(String.valueOf(model.getLikesAmount()));
        Picasso.get().load(model.getProfileImageURL()).into(holder.profileImage);
        //TODO: placeholder
        if (model.getContentImageURL() == ".") {
            holder.contentImage.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView name, timeAgo, content, likes, comments;
        ImageView profileImage, contentImage;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameHomePostItem);
            timeAgo = itemView.findViewById(R.id.timeAgoHomePostItem);
            content = itemView.findViewById(R.id.contentHomePostItem);
            likes = itemView.findViewById(R.id.likesHomePostItem);
            comments = itemView.findViewById(R.id.commentsHomePostItem);
            profileImage = itemView.findViewById(R.id.profileImageHomePostItem);
            contentImage = itemView.findViewById(R.id.contentImageHomePostItem);
        }
    }
}
