package com.szelecki.immersion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.szelecki.immersion.R;
import com.szelecki.immersion.models.ModelWord;

import java.util.ArrayList;

public class FlashcardsAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<ModelWord> models;

    FlashcardsAdapterInterface listener;

    public void setFlashcardsAdapterInterface(FlashcardsAdapterInterface listener) {
        this.listener = listener;
    }

    public FlashcardsAdapter(Context context, ArrayList<ModelWord> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.flashcards_card_item, container, false);

        CardView cardView = view.findViewById(R.id.cardViewFlashcard);
        CardView cardViewRate = view.findViewById(R.id.cardViewRateKnowFlashcard);
        TextView textViewWord = view.findViewById(R.id.textViewFlashcardsWord);
        ImageView buttonPerfect = view.findViewById(R.id.buttonFlashcardsPerfect);
        ImageView buttonAlmost = view.findViewById(R.id.buttonFlashcardsAlmost);
        ImageView buttonLittle = view.findViewById(R.id.buttonFlashcardsLittle);
        ImageView buttonNothing = view.findViewById(R.id.buttonFlashcardsNothing);

        ModelWord model = models.get(position);
        textViewWord.setText(model.getWord());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textViewWord.getText().equals(model.getWord())) {
                    textViewWord.setText(model.getMeaning());
                } else {
                    textViewWord.setText(model.getWord());
                }
            }
        });

        buttonPerfect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setupLevel(position, 4);
                cardViewRate.setVisibility(View.GONE);
            }
        });

        buttonAlmost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setupLevel(position, 3);
                cardViewRate.setVisibility(View.GONE);
            }
        });

        buttonLittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setupLevel(position, 2);
                cardViewRate.setVisibility(View.GONE);
            }
        });

        buttonNothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setupLevel(position, 1);
                cardViewRate.setVisibility(View.GONE);
            }
        });

        container.addView(view, position);
        return view;
    }

    public void setWords(ArrayList<ModelWord> models) {
        this.models = models;
    }
}