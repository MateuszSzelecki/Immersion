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
import com.szelecki.immersion.models.ModelFriendStatistic;
import com.szelecki.immersion.models.ModelLanguageStatistic;

import java.util.ArrayList;

public class NavigationViewFriendsAdapter extends RecyclerView.Adapter<NavigationViewFriendsAdapter.FriendsViewHolder> {

    private ArrayList<ModelFriendStatistic> models;
    private Context context;

    public NavigationViewFriendsAdapter(ArrayList<ModelFriendStatistic> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_navigation_view_item_friend, parent, false);
        return new FriendsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
        ModelFriendStatistic model = models.get(position);
        holder.immersionIn.setText(model.getImmersionIn());
        holder.daysFrom.setText(model.getDaysFrom());
        holder.messages.setText(model.getChatMessages());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class FriendsViewHolder extends RecyclerView.ViewHolder {
        TextView immersionIn, daysFrom, messages;
        ImageView imageView;

        public FriendsViewHolder(@NonNull View itemView) {
            super(itemView);
            immersionIn = itemView.findViewById(R.id.textViewFriendStatisticImmersionIn);
            daysFrom = itemView.findViewById(R.id.textViewFriendStatisticDaysFrom);
            messages = itemView.findViewById(R.id.textViewFriendStatisticMessages);
            imageView = itemView.findViewById(R.id.imageViewFriendStatistic);
        }
    }

    public void setFriends(ArrayList<ModelFriendStatistic> models) {
        this.models = models;
    }
}
