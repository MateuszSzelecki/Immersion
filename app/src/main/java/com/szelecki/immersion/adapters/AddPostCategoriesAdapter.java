package com.szelecki.immersion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelWord;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddPostCategoriesAdapter extends RecyclerView.Adapter<AddPostCategoriesAdapter.AddPostCategoriesViewHolder> {

    private ArrayList<String> categories;
    private ArrayList<String> chosen;
    private Context context;
    private OnSelectedCategoryAddPostInterface listener;

    public void setOnSelectedCategoryInterface(OnSelectedCategoryAddPostInterface listener) {
        this.listener = listener;
    }

    public AddPostCategoriesAdapter(ArrayList<String> categories, Context context) {
        this.categories = categories;
        this.chosen = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public AddPostCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_card_addpost_category, parent, false);
        return new AddPostCategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddPostCategoriesViewHolder holder, int position) {
        String category = categories.get(position);
        holder.textCategory.setText(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class AddPostCategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView textCategory;

        public AddPostCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            textCategory = itemView.findViewById(R.id.textCategoryHomeAddPost);

            textCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (chosen.contains(categories.get(getAdapterPosition()))) {
                        chosen.remove(categories.get(getAdapterPosition()));
                        textCategory.setBackground(context.getDrawable(R.drawable.shape_category_item_not));
                    } else {
                        if (chosen.size() < 3) {
                            chosen.add(categories.get(getAdapterPosition()));
                            textCategory.setBackground(context.getDrawable(R.drawable.shape_category_item_sel));
                            listener.onClickCategory(chosen);
                        } else {
                            Toast.makeText(context, "You can choose to 3 categories", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    public interface OnSelectedCategoryAddPostInterface {
        void onClickCategory(ArrayList<String> chosen);
    }
}
