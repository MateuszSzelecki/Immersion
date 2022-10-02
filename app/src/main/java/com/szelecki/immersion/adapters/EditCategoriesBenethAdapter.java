package com.szelecki.immersion.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.szelecki.immersion.R;

import java.util.ArrayList;

public class EditCategoriesBenethAdapter extends RecyclerView.Adapter<EditCategoriesBenethAdapter.EditBenethCategoriesViewHolder> {

    private ArrayList<String> benethCategories;
    private Context context;
    private OnSelectBenethCategoryInterface listener;
    int mainPosition;

    public EditCategoriesBenethAdapter(ArrayList<String> benethCategories, int mainPosition, Context context, OnSelectBenethCategoryInterface listener) {
        this.benethCategories = benethCategories;
        this.mainPosition = mainPosition;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EditBenethCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.editcategories_beneth_item, parent, false);
        return new EditBenethCategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditBenethCategoriesViewHolder holder, int position) {
        int backgroundColor = setupColours(mainPosition);
        String category = benethCategories.get(position);
        holder.textView.setText(category);
        holder.background.setCardBackgroundColor(backgroundColor);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSelectBenethCategory(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return benethCategories.size();
    }

    class EditBenethCategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView background;

        public EditBenethCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewItemEditBenethCategories);
            background = itemView.findViewById(R.id.cardViewItemBenethEditCategories);
        }
    }

    private int setupColours(int position) {
        int backgroundColor;
        if (position == 0 || position == 10 || position == 20) {
            backgroundColor = context.getColor(R.color.category1);
        } else if (position == 1 || position == 11 || position == 21) {
            backgroundColor = context.getColor(R.color.category2);
        } else if (position == 2 || position == 12 || position == 22) {
            backgroundColor = context.getColor(R.color.category3);
        } else if (position == 3 || position == 13) {
            backgroundColor = context.getColor(R.color.category4);
        } else if (position == 4 || position == 14) {
            backgroundColor = context.getColor(R.color.category5);
        } else if (position == 5 || position == 15) {
            backgroundColor = context.getColor(R.color.category6);
        } else if (position == 6 || position == 16) {
            backgroundColor = context.getColor(R.color.category7);
        } else if (position == 7 || position == 17) {
            backgroundColor = context.getColor(R.color.category8);
        } else if (position == 8 || position == 18) {
            backgroundColor = context.getColor(R.color.category9);
        } else if (position == 9 || position == 19) {
            backgroundColor = context.getColor(R.color.category10);
        } else {
            backgroundColor = context.getColor(R.color.category1);
        }
        return backgroundColor;
    }

    public interface OnSelectBenethCategoryInterface {
        public void onSelectBenethCategory(int benethPosition);
    }
}
