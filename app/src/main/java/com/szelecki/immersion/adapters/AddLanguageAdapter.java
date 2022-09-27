package com.szelecki.immersion.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.szelecki.immersion.R;
import com.szelecki.immersion.activities.AddLanguageActivity;
import com.szelecki.immersion.models.ModelLanguageStatistic;

import java.util.ArrayList;

public class AddLanguageAdapter extends RecyclerView.Adapter<AddLanguageAdapter.AddLanguageViewHolder> {

    private ArrayList<ModelLanguageStatistic> models;
    private ArrayList<String> representations;
    private ArrayList<Integer> chosen;
    private Context context;

    OnClickLanguageItemInterface listener;

    public void setOnClickLanguageItemInterface(OnClickLanguageItemInterface mListener) {
        this.listener = mListener;
    }

    public AddLanguageAdapter(ArrayList<ModelLanguageStatistic> models, ArrayList<String> representations, Context context) {
        this.models = models;
        this.representations = representations;
        this.context = context;
        this.chosen = new ArrayList<>();
    }

    @NonNull
    @Override
    public AddLanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.addlanguage_recyclerview_item, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (parent.getWidth() * 0.33);
        view.setLayoutParams(layoutParams);

        return new AddLanguageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddLanguageViewHolder holder, int position) {
        ModelLanguageStatistic model = models.get(position);
        holder.imageView.setImageResource(model.getImage());
        holder.description.setText(model.getName());
        holder.representation.setText(representations.get(position));

        if (model.getWords() == 1) {
            holder.background.setBackground(context.getDrawable(R.drawable.shape_addlanguage_item_sel));
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    class AddLanguageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView description, representation;
        ConstraintLayout background;

        public AddLanguageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewAddLanguageItem);
            description = itemView.findViewById(R.id.textView1AddLanguageItem);
            representation = itemView.findViewById(R.id.textView2AddLanguageItem);
            background = itemView.findViewById(R.id.backgroundAddLanguageItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!chosen.contains(getAdapterPosition()) && models.get(getAdapterPosition()).getWords() != 1) {
                        chosen.add(getAdapterPosition());
                        boolean bol = listener.addLanguage(chosen);
                        if (!bol) {
                            Toast.makeText(context, "You can add up to 4 languages", Toast.LENGTH_SHORT).show();
                        } else {
                            background.setBackground(context.getDrawable(R.drawable.shape_addlanguage_item_sel));
                        }
                    }
                }
            });
        }
    }
}
