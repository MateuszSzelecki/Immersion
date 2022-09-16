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
import com.szelecki.immersion.models.ModelPostFromFirebase;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private ArrayList<ModelPostFromFirebase> models;
    private Context context;

    PostsAdapterInterface listener;

    public void setPostsAdapterInterface(PostsAdapterInterface listener) {
        this.listener = listener;
    }

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
//        Picasso.get().load(model.getProfileImageURL()).into(holder.profileImage);
        //TODO: placeholder
        if (model.getContentImageURL().equals(".")) {
            holder.contentImage.setVisibility(View.GONE);
        }

        if (position == 9) {
            holder.loadMore.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView name, timeAgo, content, likes, comments, loadMore;
        ImageView profileImage, contentImage;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameHomePostItem);
            timeAgo = itemView.findViewById(R.id.timeAgoHomePostItem);
            content = itemView.findViewById(R.id.contentHomePostItem);
            likes = itemView.findViewById(R.id.likesHomePostItem);
            comments = itemView.findViewById(R.id.commentsHomePostItem);
            loadMore = itemView.findViewById(R.id.loadMoreTextHomePostItem);
            profileImage = itemView.findViewById(R.id.profileImageHomePostItem);
            contentImage = itemView.findViewById(R.id.contentImageHomePostItem);

            loadMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.loadMore();
                }
            });
        }
    }

    public void setPosts(ArrayList<ModelPostFromFirebase> models) {
        this.models = models;
    }
}
