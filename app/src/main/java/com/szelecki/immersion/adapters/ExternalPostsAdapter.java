package com.szelecki.immersion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelPostFirebase;

import java.util.ArrayList;

public class ExternalPostsAdapter extends RecyclerView.Adapter<ExternalPostsAdapter.RecyclerViewsViewHolder> {

    private ArrayList<ArrayList<ModelPostFirebase>> models;
    private Context context;

    public ExternalPostsAdapter(ArrayList<ArrayList<ModelPostFirebase>> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_external_item, parent, false);
        return new RecyclerViewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewsViewHolder holder, int position) {
        InternalPostsAdapter adapter = new InternalPostsAdapter(models.get(position), context);
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class RecyclerViewsViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView loadMore;

        public RecyclerViewsViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerViewHomeExternalItem);
            loadMore = itemView.findViewById(R.id.loadMoreTextHomeExternalItem);
        }
    }

}
