package com.szelecki.immersion.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.szelecki.immersion.R;

import java.util.ArrayList;

public class EditCategoriesMainAdapter extends RecyclerView.Adapter<EditCategoriesMainAdapter.EditCategoriesViewHolder> {

    private ArrayList<String> mainCategories;
    private Context context;
    private OnCategoryChooseInterface listener;

    public EditCategoriesMainAdapter(ArrayList<String> mainCategories, Context context, OnCategoryChooseInterface listener) {
        this.mainCategories = mainCategories;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EditCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.editcategories_main_item, parent, false);
        return new EditCategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditCategoriesViewHolder holder, int position) {
        Pair<Integer, Integer> colours = setupColours(position);
        int backgroundColor = colours.first;
        int textColor = colours.second;
        String category = mainCategories.get(position);
        holder.textView.setText(category);
        holder.background.setCardBackgroundColor(backgroundColor);
        holder.textView.setTextColor(textColor);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.showMoreCategories(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainCategories.size();
    }

    class EditCategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView background;

        public EditCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewItemEditCategories);
            background = itemView.findViewById(R.id.cardViewItemEditCategories);
        }
    }

    private Pair<Integer, Integer> setupColours(int position) {
        int backgroundColor, textColor;
        if (position == 0 || position == 10 || position == 20) {
            backgroundColor = context.getColor(R.color.category1);
            textColor = context.getColor(R.color.main_black);
        } else if (position == 1 || position == 11 || position == 21) {
            backgroundColor = context.getColor(R.color.category2);
            textColor = context.getColor(R.color.main_black);
        } else if (position == 2 || position == 12 || position == 22) {
            backgroundColor = context.getColor(R.color.category3);
            textColor = context.getColor(R.color.main_black);
        } else if (position == 3 || position == 13) {
            backgroundColor = context.getColor(R.color.category4);
            textColor = context.getColor(R.color.main_black);
        } else if (position == 4 || position == 14) {
            backgroundColor = context.getColor(R.color.category5);
            textColor = context.getColor(R.color.main_black);
        } else if (position == 5 || position == 15) {
            backgroundColor = context.getColor(R.color.category6);
            textColor = context.getColor(R.color.white);
        } else if (position == 6 || position == 16) {
            backgroundColor = context.getColor(R.color.category7);
            textColor = context.getColor(R.color.white);
        } else if (position == 7 || position == 17) {
            backgroundColor = context.getColor(R.color.category8);
            textColor = context.getColor(R.color.white);
        } else if (position == 8 || position == 18) {
            backgroundColor = context.getColor(R.color.category9);
            textColor = context.getColor(R.color.dark_white);
        } else if (position == 9 || position == 19) {
            backgroundColor = context.getColor(R.color.category10);
            textColor = context.getColor(R.color.dark_white);
        } else {
            backgroundColor = context.getColor(R.color.category1);
            textColor = context.getColor(R.color.main_black);
        }
        return Pair.create(backgroundColor, textColor);
    }

    public interface OnCategoryChooseInterface {
        public void showMoreCategories(int position);
    }
}
